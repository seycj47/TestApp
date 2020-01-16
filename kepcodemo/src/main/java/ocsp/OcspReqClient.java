package ocsp;

import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.ocsp.CrlID;
import org.bouncycastle.asn1.ocsp.OCSPObjectIdentifiers;
import org.bouncycastle.asn1.ocsp.ServiceLocator;
import org.bouncycastle.asn1.x509.*;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.cert.ocsp.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.bc.BcDigestCalculatorProvider;
import ptca.PTCACryptoSession;
import util.CertAndKeyUtil;

import org.apache.commons.io.IOUtils;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.*;

public class OcspReqClient {

    byte[] sampleNonce; //Nonce
    List<OcspReqCert> reqCertList; //Request List
    String ocspSvrUrl; //VA URL

    static {
        Security.addProvider(new BouncyCastleProvider()); //Load BouncyCastle
    }

    public OcspReqClient(String ocspSvrUrl) throws CertificateException, NoSuchProviderException {
        this.ocspSvrUrl = ocspSvrUrl;
        this.reqCertList = new ArrayList();
    }

    // cert binary to X509 Class
    private static X509Certificate certToValue(byte[] cert) throws CertificateException, NoSuchProviderException {
        InputStream certInputStream = new ByteArrayInputStream(cert);
        CertificateFactory certFactory = CertificateFactory.getInstance("X.509", BouncyCastleProvider.PROVIDER_NAME);
        return (X509Certificate) certFactory.generateCertificate(certInputStream);
    }

    private byte[] sendTcp(String ip, int port, byte[] ocspReqBytes) {
        System.out.println("sendTcp");
        Socket mSocket = null;
        try {
            mSocket = new Socket(ip, port);
            OutputStream os = mSocket.getOutputStream();
            os.write(ocspReqBytes);
            os.flush();
            InputStream is = mSocket.getInputStream();
            byte[] ocspResponse = new byte[10000];
            is.read(ocspResponse);
            mSocket.close();
            return ocspResponse;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private byte[] sendGet(byte[] ocspReqBytes) {
        System.out.println("sendGet");
        try {
            //Base64 + URL Encode for HTTP
            String ocspReq = URLEncoder.encode(Base64.getEncoder().encodeToString(ocspReqBytes), "UTF-8");

            URL url = new URL(this.ocspSvrUrl + "/" + ocspReq);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);
            /*
            try (InputStream in = conn.getInputStream(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                byte[] buf = new byte[1024 * 8];
                int length = 0;
                while ((length = in.read(buf)) != -1) {
                    out.write(buf, 0, length);
                }
                conn.disconnect();
                return Base64.getDecoder().decode(URLDecoder.decode(out.toString(), "UTF-8"));
            }
            */

            return IOUtils.toByteArray(conn.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] sendPost(byte[] ocspReqBytes) {
        System.out.println("sendPost");
        try {

            final String USER_AGENT = "Mozilla/5.0";

            URL obj = new URL(this.ocspSvrUrl);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Setting basic post request
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            con.setRequestProperty("Content-Type", "application/ocsp-request");

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.write(ocspReqBytes);
            wr.flush();
            wr.close();
            /*
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String output;
            StringBuffer response = new StringBuffer();
            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            in.close();
             */
            return IOUtils.toByteArray(con.getInputStream());
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    public int add(String userCert, String issuerCert, String crlDp, String ocspUrl)
            throws CertificateException, NoSuchProviderException {
        OcspReqCert ocspReqCert = new OcspReqCert();

        byte[] caCertificateData = Base64.getDecoder().decode(issuerCert);
        X509Certificate caCert = certToValue(caCertificateData);

        byte[] userCertificateData = Base64.getDecoder().decode(userCert);

        ocspReqCert.userCert = certToValue(userCertificateData);
        ocspReqCert.caCertHolder = new JcaX509CertificateHolder(caCert);
        ocspReqCert.crlDp = crlDp;
        ocspReqCert.ocspUrl = ocspUrl;

        this.reqCertList.add(ocspReqCert);

        return 0;
    }

    public byte[] buildReq() {
        try {
            // Create OCSP Request
            OCSPReqBuilder reqBuilder = new OCSPReqBuilder();

            for (OcspReqCert reqCert : this.reqCertList) {
                X509Certificate userCert = reqCert.userCert;

                // Create Request CertID
                DigestCalculator digestCalculator;
                digestCalculator = new BcDigestCalculatorProvider().get(CertificateID.HASH_SHA1);

                CertificateID id = new CertificateID(digestCalculator, reqCert.caCertHolder,
                        userCert.getSerialNumber());

                ExtensionsGenerator extensionsGenerator = new ExtensionsGenerator();

                if (reqCert.ocspUrl != null) {
                    GeneralName generalName = new GeneralName(GeneralName.uniformResourceIdentifier, reqCert.ocspUrl);
                    AccessDescription accessDescription = new AccessDescription(AccessDescription.id_ad_ocsp , generalName);

                    ASN1EncodableVector vec = new ASN1EncodableVector();
                    vec.add(reqCert.caCertHolder.getSubject());
                    vec.add(new AuthorityInformationAccess(accessDescription));

                    ServiceLocator serviceLocator = ServiceLocator.getInstance(new DERSequence(vec));

                    extensionsGenerator.addExtension(OCSPObjectIdentifiers.id_pkix_ocsp_service_locator, false, serviceLocator);
                }

                if (reqCert.crlDp != null) {
                    ASN1EncodableVector vec = new ASN1EncodableVector();
                    vec.add(new DERTaggedObject(true, 0, new DERIA5String(reqCert.crlDp, true)));
                    vec.add(new DERTaggedObject(true, 1, new ASN1Integer(0))); // Optional
                    vec.add(new DERTaggedObject(true, 2, new DERGeneralizedTime(new Date()))); // Optional

                    CrlID crlid = CrlID.getInstance(new DERSequence(vec));

                    extensionsGenerator.addExtension(OCSPObjectIdentifiers.id_pkix_ocsp_crl, false,
                            crlid.toASN1Primitive());
                }
                if (extensionsGenerator.isEmpty()) {
                    reqBuilder.addRequest(id);
                } else {
                    reqBuilder.addRequest(id, extensionsGenerator.generate());
                }
            }

            // Create nonce Extension
            sampleNonce = new byte[16];
            Random rand = new Random();
            rand.nextBytes(sampleNonce);
            ExtensionsGenerator extensionsGenerator = new ExtensionsGenerator();
            extensionsGenerator.addExtension(OCSPObjectIdentifiers.id_pkix_ocsp_nonce, false,
                    new DEROctetString(sampleNonce));
            reqBuilder.setRequestExtensions(extensionsGenerator.generate());

            // Build Request Data
            OCSPReq ocspReqCli = reqBuilder.build();
            byte[] ocsrReqBytes = ocspReqCli.getEncoded();

            return ocsrReqBytes;
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        }
    }

    public void validOcspResponse(byte[] ocspRespBytes) throws Exception {
        // Process Response
        OCSPResp ocspRespCli = new OCSPResp(ocspRespBytes);

        System.out.println("ocspRespBytes : " + Base64.getEncoder().encodeToString(ocspRespBytes));
        System.out.println("sampleNonce : " + Base64.getEncoder().encodeToString(sampleNonce));
        if (ocspRespCli.getStatus() == 0) {
            BasicOCSPResp bRes = (BasicOCSPResp) ocspRespCli.getResponseObject();

            // Check Nonce
            boolean nounceCheck = false;
            Extension temp = bRes.getExtension(OCSPObjectIdentifiers.id_pkix_ocsp_nonce);
            ASN1Encodable tmpByte = temp.getParsedValue();
            byte[] nonce = ((ASN1OctetString) tmpByte).getOctets();
            for (int u = 0; u < nonce.length; u++) {
                if (nonce[u] != sampleNonce[u]) {
                    nounceCheck = false;
                    break;
                }
                nounceCheck = true;
            }

            if (nounceCheck) {
                X509CertificateHolder[] chains = bRes.getCerts();
                if (checkValidity(chains)) {
//                    JcaContentVerifierProviderBuilder jcaContentVerifierProviderBuilder = new JcaContentVerifierProviderBuilder();
//                    jcaContentVerifierProviderBuilder.setProvider(BouncyCastleProvider.PROVIDER_NAME);
//                    ContentVerifierProvider contentVerifierProvider = jcaContentVerifierProviderBuilder.build(chains[0]);
//                    char[] chr = new char[nonce.length];
//                    for (int i=0; i<nonce.length; i++) {
//                        chr[i] = (char)nonce[i];
//                    }
//                    chr = new char[0];
//
//                    boolean verify = CisJna.getInstance().verifyOCSP(ocspRespBytes, chr);


                    PublicKey publicKey = CertAndKeyUtil.BytearrayToPublicKey(chains[0]);
                    PTCACryptoSession ptcaCryptoSession = new PTCACryptoSession(publicKey, 1);
                    boolean verify = ptcaCryptoSession.verify(bRes.getSignatureAlgorithmID(), bRes.getSignature(), bRes.getTBSResponseData());


                    //verify = bRes.isSignatureValid(contentVerifierProvider);
                    if (verify) {
                        SingleResp[] respList = bRes.getResponses();
                        for (SingleResp resp : respList) {
                            CertificateID certID = resp.getCertID();
                            Object status = resp.getCertStatus();
                            if (status instanceof UnknownStatus) {
                                System.out.println("[" + certID.getSerialNumber() + "] : Unknown");
                            } else if (status instanceof RevokedStatus) {
                                System.out.println("[" + certID.getSerialNumber() + "] : Revoked");
                            } else {
                                System.out.println("[" + certID.getSerialNumber() + "] : Good");
                            }
                        }
                    }
                } // chain verify
            } // nounceCheck
        } // response.getStatus()
        else {
            System.out.println(ocspRespCli.getStatus());
        }
    }

    public boolean checkValidity(X509CertificateHolder[] certs) {
        int n = certs.length;
        try {
            for (int i = 0; i < n - 1; i++) {
                X509Certificate cert = CertAndKeyUtil.BytearrayToX509Certificate(new ByteArrayInputStream(certs[i].getEncoded()));
                X509Certificate issuer = CertAndKeyUtil.BytearrayToX509Certificate(new ByteArrayInputStream(certs[i+1].getEncoded()));

                X509CertificateHolder certHolder = certs[i];
                X509CertificateHolder issuerHolder = certs[i+1];

                if (cert.getIssuerX500Principal().equals(issuer.getSubjectX500Principal()) == false) {
                    throw new Exception("Certificates do not chain");
                }

                PublicKey publicKey = CertAndKeyUtil.BytearrayToPublicKey(issuerHolder);

                // issuer public key
                PTCACryptoSession cryptoSession = new PTCACryptoSession(publicKey, 1);
                boolean verifySuccess = cryptoSession.verify(certHolder.getSignatureAlgorithm(), cert.getSignature(), cert.getTBSCertificate());
                if (!verifySuccess) {
                    throw new Exception("Certificate chain verify fail.");
                }
            }
            X509Certificate last = CertAndKeyUtil.BytearrayToX509Certificate(new ByteArrayInputStream(certs[n-1].getEncoded()));
            X509CertificateHolder lastHolder = certs[n-1];
            // if self-signed, verify the final cert
            if (last.getIssuerX500Principal().equals(last.getSubjectX500Principal())) {
                PublicKey publicKey = CertAndKeyUtil.BytearrayToPublicKey(lastHolder);
                PTCACryptoSession cryptoSession = new PTCACryptoSession(publicKey, 1);
                boolean verifySuccess = cryptoSession.verify(lastHolder.getSignatureAlgorithm(), last.getSignature(), last.getTBSCertificate());
                if (!verifySuccess) {
                    throw new Exception("Certificate chain verify fail.");
                }
            } else {
                throw new Exception("Does not exist root certificate;");
            }
        } catch (Exception e) {
            System.out.println("checkValidity : " + e.getMessage());
            return false;
        }

        return true;
    }

}
