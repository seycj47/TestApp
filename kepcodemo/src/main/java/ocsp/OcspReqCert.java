package ocsp;

import org.bouncycastle.cert.X509CertificateHolder;

import java.security.cert.X509Certificate;

public class OcspReqCert {
    X509CertificateHolder caCertHolder; //Issuer Cert
    X509Certificate userCert;  //Valid Target Cert
    String crlDp; //CRLDP Extention
    String ocspUrl; // Service Locator
}
