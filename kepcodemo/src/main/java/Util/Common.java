package Util;

import message.*;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
import org.bouncycastle.math.ec.ECPoint;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.xml.bind.JAXBElement;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.util.*;

public class Common {

    final String PASSPHRASE_FOR_CERTIFICATES_AND_KEYS = "Autocrypt123!";
    final String ALIAS_PFX_PRIVATEKEY = "PFXCertificate";
    final String oemCert = "MIICzTCCAnOgAwIBAgIGAOjUpRADMAoGCCqGSM49BAMCMFwxITAfBgNVBAMMGFBLSS0xX0NSVF9PRU1fU1VCMl9WQUxJRDEVMBMGA1UECgwMdmVyaXNjbyBHbWJIMQswCQYDVQQGEwJERTETMBEGCgmSJomT8ixkARkWA09FTTAeFw0xOTExMDYwMDAwMDBaFw0yOTExMDYyMzU5NTlaMGsxCzAJBgNVBAYTAkRFMRUwEwYDVQQKDAx2ZXJpc2NvIEdtYkgxEjAQBgNVBAsMCWF1dG9jcnlwdDETMBEGCgmSJomT8ixkARkWA09FTTEcMBoGA1UEAwwTUEtJLTFfQ1JUX09FTV9MRUFGMTBZMBMGByqGSM49AgEGCCqGSM49AwEHA0IABDmdgqPWVy0Yw6ey56/2pd9M1BvBKw+/sEWtH/37lOdD2Vp1BtIAHBpmUUmQBk7XGDCr/ikvCLEWyaqNJEhculSjggEQMIIBDDCBhAYDVR0jBH0we4AUdEwEHbNhMelxXYdOmBpbdn5rTAqhYKReMFwxITAfBgNVBAMMGFBLSS0xX0NSVF9PRU1fU1VCMV9WQUxJRDEVMBMGA1UECgwMdmVyaXNjbyBHbWJIMQswCQYDVQQGEwJERTETMBEGCgmSJomT8ixkARkWA09FTYIBCDAdBgNVHQ4EFgQU75VLv9PVL+oMwY/XWNFcwUpV+bMwDgYDVR0PAQH/BAQDAgOIMFQGA1UdHwRNMEswSaBHoEWGQ2NuPWRwX21uSlA0ZnRhUjlHLXlTaXV4bVNsSUFwMCxvdT1hdXRvY3J5cHQsbz12ZXJpc2NvIEdtYkgsYz1ERS5jcmwwCgYIKoZIzj0EAwIDSAAwRQIgdbYXpvv9dnLg0b/71nczbX/R6ExKz5LBW1Dl6X1Q68sCIQCRKo9qY9cxIVIHulI8gweIV3oywEdpDiPYVsjcJnnf0w==";
    final String oemPfxCert = "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";

    private CertFactory certFactory = new CertFactory();
    private CryptFactory cryptFactory = new CryptFactory();


    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();

        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());

        return base64Encode(bb.array());
    }

    public static String base64Encode(byte[] str) {
        return Base64.getEncoder().encodeToString(str);
    }
    public static byte[] base64Decode(String str) {
        return Base64.getDecoder().decode(str);
    }
    public static byte[] hash(String alg, byte[] arr) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(alg).digest(arr);
    }


    public V2GMessage createCertUpdateReq2(String updtResMsg) throws Exception {
        String decodedMsg = ExiFactory.getInstance().decodeEXI(updtResMsg, false, false);
        V2GMessage resV2gMsg = (V2GMessage) MsgDigest.unmarshallToMessage(decodedMsg.getBytes(), V2GMessage.class);

        JAXBElement<CertificateUpdateResType> certUpdtRes = (JAXBElement<CertificateUpdateResType>) resV2gMsg.getBody().getBodyElement();

        //. Create CertificateInstallationReq
        X509IssuerSerialType x509IssuerSerial = new X509IssuerSerialType();
        x509IssuerSerial.setX509IssuerName("DC=V2G,C=DE,O=verisco GmbH,CN=PKI-1_CRT_V2G_ROOT_VALID");
        x509IssuerSerial.setX509SerialNumber(BigInteger.ONE);

        List<X509IssuerSerialType> rootCertificateIDs = Arrays.asList(x509IssuerSerial); // new ArrayList<>();

//        CertificateInstallationReqType certInstReq = new CertificateInstallationReqType();

        CertificateChainType certChain = new CertificateChainType();
        certChain.setCertificate(certUpdtRes.getValue().getContractSignatureCertChain().getCertificate());
        SubCertificatesType subCertType = new SubCertificatesType();
        subCertType.setSubCertificate(certUpdtRes.getValue().getContractSignatureCertChain().getSubCertificates().getSubCertificate());
        certChain.setSubCertificates(subCertType);


        CertificateUpdateReqType certUpdtReq = new CertificateUpdateReqType();
        certUpdtReq.setId("ID1");
        certUpdtReq.setContractSignatureCertChain(certChain);
        certUpdtReq.setListOfRootCertificateIDs(new ListOfRootCertificateIDsType());
        certUpdtReq.getListOfRootCertificateIDs().setRootCertificateID(rootCertificateIDs);
        certUpdtReq.setEMAID(certUpdtRes.getValue().getEMAID().getValue());


        // signature 생성
        Map targetMap = new HashMap<Class<?>, Object>() {{
            put(CertificateUpdateReqType.class, certUpdtReq);
        }};


        //ING
        byte[] encryptedPriKeyBytes = certUpdtRes.getValue().getContractSignatureEncryptedPrivateKey().getValue();
        byte[] dhPubKeyBytes = certUpdtRes.getValue().getDHpublickey().getValue();

        KeyStore keystore = certFactory.getPKCS12KeyStore(oemPfxCert, PASSPHRASE_FOR_CERTIFICATES_AND_KEYS);

        java.security.interfaces.ECPrivateKey oemPrivKey = (java.security.interfaces.ECPrivateKey)keystore.getKey(ALIAS_PFX_PRIVATEKEY, "".toCharArray());


//        val key1: java.security.interfaces.ECPrivateKey? = null
//        val key2: ECPrivateKey? = null


        KeyFactory keyFactory = KeyFactory.getInstance("EC", "BC");
        //CURVE_NAME e.g prime192v1
        ECNamedCurveParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256r1");

        //pub Decoding and create ECPubKey
        ECPoint point = ecSpec.getCurve().decodePoint(dhPubKeyBytes);
        ECPublicKeySpec pubSpec = new ECPublicKeySpec(point, ecSpec);
        ECPublicKey dhPubKey = (ECPublicKey)keyFactory.generatePublic(pubSpec);

        //decrypt encrypted privKey
        byte[] secretKey = cryptFactory.generateSharedSecret(oemPrivKey, (ECPublicKey)dhPubKey);
        SecretKey sessionKey = cryptFactory.generateSessionKey(secretKey);
//        val randomIV = generateRandom(16)
        byte[] randomIV = new byte[16];
        System.arraycopy(encryptedPriKeyBytes, 0, randomIV, 0, 16);

        IvParameterSpec ivParamSpec = new IvParameterSpec(randomIV);
        byte[] realEncrypedPrivKey = new byte[32];
        System.arraycopy(encryptedPriKeyBytes, 16, realEncrypedPrivKey, 0, 32);

        byte[] decryptedPriv = cryptFactory.decrypt(realEncrypedPrivKey, sessionKey, ivParamSpec);

        // create ECPrivKey
        ECPrivateKeySpec privSpec = new ECPrivateKeySpec(new BigInteger(decryptedPriv), ecSpec);
        PrivateKey privateKey = keyFactory.generatePrivate(privSpec);


        SignatureType signature = new SignatureType();
        signature.setSignedInfo(createSignedInfo(targetMap));

        JAXBElement<SignedInfoType> signedInfoJAXB = ObjectFactory.createSignedInfoType(signature.getSignedInfo());


        // signature 생성
        signature.setSignatureValue(MsgDigest.signSignedInfoElement(signedInfoJAXB, (java.security.interfaces.ECPrivateKey)privateKey));


//        SignatureType signature = Common.createSignature(targetMap, oemPfxCert);

        // 3. V2G Message 생성
        V2gHeaderType v2gHeader = new V2gHeaderType();
        v2gHeader.setSessionId("355E336113C0BE9D");
        v2gHeader.setSignature(signature);
        BodyType bodyMsg = new BodyType();
        bodyMsg.setBodyElement(ObjectFactory.createCertificateUpdateReq(certUpdtReq));
        V2GMessage v2gMsg = new V2GMessage();
        v2gMsg.setHeader(v2gHeader);
        v2gMsg.setBody(bodyMsg);

        return v2gMsg;
    }
    public V2GMessage createCertUpdateReq(String instResMsg) throws Exception {
        String decodedMsg = ExiFactory.getInstance().decodeEXI(instResMsg, false, false);
        V2GMessage resV2gMsg = (V2GMessage) MsgDigest.unmarshallToMessage(decodedMsg.getBytes(), V2GMessage.class);

        JAXBElement<CertificateInstallationResType> certInstRes = (JAXBElement<CertificateInstallationResType>) resV2gMsg.getBody().getBodyElement();

        //. Create CertificateInstallationReq
        X509IssuerSerialType x509IssuerSerial = new X509IssuerSerialType();
        x509IssuerSerial.setX509IssuerName("DC=V2G,C=DE,O=verisco GmbH,CN=PKI-1_CRT_V2G_ROOT_VALID");
        x509IssuerSerial.setX509SerialNumber(BigInteger.ONE);

        List<X509IssuerSerialType> rootCertificateIDs = Arrays.asList(x509IssuerSerial); // new ArrayList<>();

//        CertificateInstallationReqType certInstReq = new CertificateInstallationReqType();

        CertificateChainType certChain = new CertificateChainType();
        certChain.setCertificate(certInstRes.getValue().getContractSignatureCertChain().getCertificate());
        SubCertificatesType subCertType = new SubCertificatesType();
        subCertType.setSubCertificate(certInstRes.getValue().getContractSignatureCertChain().getSubCertificates().getSubCertificate());
        certChain.setSubCertificates(subCertType);


        CertificateUpdateReqType certUpdtReq = new CertificateUpdateReqType();
        certUpdtReq.setId("ID1");
        certUpdtReq.setContractSignatureCertChain(certChain);
        certUpdtReq.setListOfRootCertificateIDs(new ListOfRootCertificateIDsType());
        certUpdtReq.getListOfRootCertificateIDs().setRootCertificateID(rootCertificateIDs);
        certUpdtReq.setEMAID(certInstRes.getValue().getEMAID().getValue());


        // signature 생성
        Map targetMap = new HashMap<Class<?>, Object>() {{
            put(CertificateUpdateReqType.class, certUpdtReq);
        }};


        //ING
        byte[] encryptedPriKeyBytes = certInstRes.getValue().getContractSignatureEncryptedPrivateKey().getValue();
        byte[] dhPubKeyBytes = certInstRes.getValue().getDHpublickey().getValue();

        KeyStore keystore = certFactory.getPKCS12KeyStore(oemPfxCert, PASSPHRASE_FOR_CERTIFICATES_AND_KEYS);

        java.security.interfaces.ECPrivateKey oemPrivKey = (java.security.interfaces.ECPrivateKey)keystore.getKey(ALIAS_PFX_PRIVATEKEY, "".toCharArray());


//        val key1: java.security.interfaces.ECPrivateKey? = null
//        val key2: ECPrivateKey? = null


        KeyFactory keyFactory = KeyFactory.getInstance("EC", "BC");
        //CURVE_NAME e.g prime192v1
        ECNamedCurveParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256r1");

        //pub Decoding and create ECPubKey
        ECPoint point = ecSpec.getCurve().decodePoint(dhPubKeyBytes);
        ECPublicKeySpec pubSpec = new ECPublicKeySpec(point, ecSpec);
        ECPublicKey dhPubKey = (ECPublicKey)keyFactory.generatePublic(pubSpec);

        //decrypt encrypted privKey
        byte[] secretKey = cryptFactory.generateSharedSecret(oemPrivKey, (ECPublicKey)dhPubKey);
        SecretKey sessionKey = cryptFactory.generateSessionKey(secretKey);
//        val randomIV = generateRandom(16)
        byte[] randomIV = new byte[16];
        System.arraycopy(encryptedPriKeyBytes, 0, randomIV, 0, 16);

        IvParameterSpec ivParamSpec = new IvParameterSpec(randomIV);
        byte[] realEncrypedPrivKey = new byte[32];
        System.arraycopy(encryptedPriKeyBytes, 16, realEncrypedPrivKey, 0, 32);

        byte[] decryptedPriv = cryptFactory.decrypt(realEncrypedPrivKey, sessionKey, ivParamSpec);

        // create ECPrivKey
        ECPrivateKeySpec privSpec = new ECPrivateKeySpec(new BigInteger(decryptedPriv), ecSpec);
        PrivateKey privateKey = keyFactory.generatePrivate(privSpec);


        SignatureType signature = new SignatureType();
        signature.setSignedInfo(createSignedInfo(targetMap));

        JAXBElement<SignedInfoType> signedInfoJAXB = ObjectFactory.createSignedInfoType(signature.getSignedInfo());


        // signature 생성
        signature.setSignatureValue(MsgDigest.signSignedInfoElement(signedInfoJAXB, (java.security.interfaces.ECPrivateKey)privateKey));


//        SignatureType signature = Common.createSignature(targetMap, oemPfxCert);

        // 3. V2G Message 생성
        V2gHeaderType v2gHeader = new V2gHeaderType();
        v2gHeader.setSessionId("355E336113C0BE9D");
        v2gHeader.setSignature(signature);
        BodyType bodyMsg = new BodyType();
        bodyMsg.setBodyElement(ObjectFactory.createCertificateUpdateReq(certUpdtReq));
        V2GMessage v2gMsg = new V2GMessage();
        v2gMsg.setHeader(v2gHeader);
        v2gMsg.setBody(bodyMsg);

        return v2gMsg;
    }

    public V2GMessage createCertInstallReq() throws Exception {

        // 1. CertificateInstallationReq Msg 생성
        X509IssuerSerialType x509IssuerSerial = new X509IssuerSerialType();
        x509IssuerSerial.setX509IssuerName("DC=V2G,C=DE,O=verisco GmbH,CN=PKI-1_CRT_V2G_ROOT_VALID");
        x509IssuerSerial.setX509SerialNumber(BigInteger.ONE);

        List<X509IssuerSerialType> rootCertificateIDs = Arrays.asList(x509IssuerSerial); // new ArrayList<>();

        CertificateInstallationReqType certInstReq = new CertificateInstallationReqType();

        ListOfRootCertificateIDsType listOfRootCertificateIDsType = new ListOfRootCertificateIDsType();
        listOfRootCertificateIDsType.setRootCertificateID(rootCertificateIDs);

        certInstReq.setId("ID1");
        certInstReq.setOEMProvisioningCert(base64Decode(oemCert));
        certInstReq.setListOfRootCertificateIDs(listOfRootCertificateIDsType);

        // 2. signature 생성
        // sign target
        Map<Class<?>, Object> targetMap = new HashMap<Class<?>, Object>() {{
            put(CertificateInstallationReqType.class, certInstReq);
        }};

        SignatureType signature = createSignature(targetMap, oemPfxCert);

        // 3. V2G Message 생성
        V2gHeaderType v2gHeader = new V2gHeaderType();
        v2gHeader.setSessionId("355E336113C0BE9D");
        v2gHeader.setSignature(signature);
        BodyType bodyMsg = new BodyType();
        bodyMsg.setBodyElement(ObjectFactory.createCertificateInstallationReq(certInstReq));
        V2GMessage v2gMsg = new V2GMessage();
        v2gMsg.setHeader(v2gHeader);
        v2gMsg.setBody(bodyMsg);

        return v2gMsg;
    }

    public SignatureType createSignature(Map<Class<?>, Object> targetMap, String pfx) throws Exception {
        CertFactory certFactory = new CertFactory();
        SignatureType signature = new SignatureType();

        signature.setSignedInfo(createSignedInfo(targetMap));
        JAXBElement<SignedInfoType> signedInfoJAXB = ObjectFactory.createSignedInfoType(signature.getSignedInfo());

        KeyStore keystore = certFactory.getPKCS12KeyStore(pfx, PASSPHRASE_FOR_CERTIFICATES_AND_KEYS);
        java.security.interfaces.ECPrivateKey ecPrivateKey = certFactory.getPrivateKey(keystore, "PFXCertificate");
        signature.setSignatureValue(MsgDigest.signSignedInfoElement(signedInfoJAXB, ecPrivateKey));

        return signature;
    }

    public SignedInfoType createSignedInfo(Map<Class<?>, Object> targetMap) throws Exception {
        SignedInfoType signedInfo = new SignedInfoType();

        List<ReferenceType> list = new ArrayList<ReferenceType>();

        for (Class key : targetMap.keySet()) {
            Object value = targetMap.get(key);
            ReferenceType digest = new ReferenceType();
            digest.setTransforms(new TransformsType());
            byte[] digestBytes = null;

            String className = key.getName();
            className = className.substring(className.lastIndexOf(".")+1);
            switch(className) {
                case "CertificateUpdateReqType" :
                    CertificateUpdateReqType obj = (CertificateUpdateReqType)value;
                    digest.setURI("#" + obj.getId());
                    JAXBElement<CertificateUpdateReqType> jaxb = ObjectFactory.createCertificateUpdateReq(obj);
                    digestBytes = MsgDigest.generateDigest(jaxb, key, false);
                    break;
                case "CertificateInstallationReqType" :
                    CertificateInstallationReqType certInstReqObj = (CertificateInstallationReqType)value;
                    digest.setURI("#" + certInstReqObj.getId());
                    JAXBElement<CertificateInstallationReqType> certInstReqJaxb = ObjectFactory.createCertificateInstallationReq(certInstReqObj);
                    digestBytes = MsgDigest.generateDigest(certInstReqJaxb, key, false);
                    break;
                case "ContractSignatureEncryptedPrivateKeyType" :
                    ContractSignatureEncryptedPrivateKeyType encryptedPrivateKeyObj = (ContractSignatureEncryptedPrivateKeyType) value;
                    digest.setURI("#" + encryptedPrivateKeyObj.getId());
                    JAXBElement<ContractSignatureEncryptedPrivateKeyType> encryptedPrivateKeyJAXB = ObjectFactory.createEncryptedPrivateKeyType(encryptedPrivateKeyObj);
                    digestBytes = MsgDigest.generateDigest(encryptedPrivateKeyJAXB, key, false);
                    break;
                case "CertificateChainType" :
                    CertificateChainType contractCertChainObj = (CertificateChainType) value;
                    digest.setURI("#" + contractCertChainObj.getId());
                    JAXBElement<CertificateChainType>  contractCertChainJAXB = ObjectFactory.createCertChainType(contractCertChainObj);
                    digestBytes = MsgDigest.generateDigest(contractCertChainJAXB, key, false);
                    break;
                case "EMAIDType" :
                    EMAIDType emaidObj = (EMAIDType) value;
                    digest.setURI("#" + emaidObj.getId());
                    JAXBElement<EMAIDType>  emaidJAXB = ObjectFactory.createEmaidType(emaidObj);
                    digestBytes = MsgDigest.generateDigest(emaidJAXB, key, false);
                    break;
                case "DiffieHellmanPublickeyType" :
                    DiffieHellmanPublickeyType dhPubKeyObj = (DiffieHellmanPublickeyType) value;
                    digest.setURI("#" + dhPubKeyObj.getId());
                    JAXBElement<DiffieHellmanPublickeyType> dhPubKeyJAXB = ObjectFactory.createDhPublicKeyType(dhPubKeyObj);
                    digestBytes = MsgDigest.generateDigest(dhPubKeyJAXB, key, false);
                    break;
            }
            digest.setDigestValue(digestBytes);
            list.add(digest);
        }

        signedInfo.setReference(list);
        return signedInfo;
    }
}
