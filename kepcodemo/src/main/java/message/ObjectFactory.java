package message;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {

    private final static QName _CertificateInstallationRes_QNAME = new QName("urn:iso:15118:2:2013:MsgBody", "CertificateInstallationRes");
    private final static QName _CertificateInstallationReq_QNAME = new QName("urn:iso:15118:2:2013:MsgBody", "CertificateInstallationReq");
    private final static QName _CertificateUpdateRes_QNAME = new QName("urn:iso:15118:2:2013:MsgBody", "CertificateUpdateRes");
    private final static QName _CertificateUpdateReq_QNAME = new QName("urn:iso:15118:2:2013:MsgBody", "CertificateUpdateReq");
    private final static QName _BodyElement_QNAME = new QName("urn:iso:15118:2:2013:MsgBody", "BodyElement");
    private final static QName _ContractSignatureEncryptedPrivateKey_QNAME = new QName("urn:iso:15118:2:2013:MsgBody", "ContractSignatureEncryptedPrivateKey");
    private final static QName _DHpublickey_QNAME = new QName("urn:iso:15118:2:2013:MsgBody", "DHpublickey");
    private final static QName _ContractSignatureCertChain_QNAME = new QName("urn:iso:15118:2:2013:MsgBody", "ContractSignatureCertChain");
    private final static QName _eMAID_QNAME = new QName("urn:iso:15118:2:2013:MsgBody", "eMAID");
    private final static QName _SignedInfo_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "SignedInfo");
    private final static QName _Signature_QNAME = new QName("http://www.w3.org/2000/09/xmldsig#", "Signature");


//    fun createCertificateInstallationResType(): CertInstResMsg {
//        return new CertInstResMsg()
//    }
//
//    fun createCertificateUpdateResType(): CertUpdtResMsg {
//        return new CertUpdtResMsg()
//    }


    @XmlElementDecl(namespace = "urn:iso:15118:2:2013:MsgBody", name = "BodyElement")
    public static JAXBElement<BodyBaseType> createBodyElement(BodyBaseType value) {
        return new JAXBElement<BodyBaseType>(_BodyElement_QNAME, BodyBaseType.class, null, value);
    }

    @XmlElementDecl(namespace = "urn:iso:15118:2:2013:MsgBody", name = "ContractSignatureCertChain")
    public static JAXBElement<CertificateChainType> createCertChainType(CertificateChainType value) {
        return new JAXBElement<CertificateChainType>(_ContractSignatureCertChain_QNAME, CertificateChainType.class, null, value);
    }

    @XmlElementDecl(namespace = "urn:iso:15118:2:2013:MsgBody", name = "ContractSignatureEncryptedPrivateKey")
    public static JAXBElement<ContractSignatureEncryptedPrivateKeyType> createEncryptedPrivateKeyType(ContractSignatureEncryptedPrivateKeyType value) {
        return new JAXBElement<ContractSignatureEncryptedPrivateKeyType>(_ContractSignatureEncryptedPrivateKey_QNAME, ContractSignatureEncryptedPrivateKeyType.class, null, value);
    }

    @XmlElementDecl(namespace = "urn:iso:15118:2:2013:MsgBody", name = "eMAID")
    public static JAXBElement<EMAIDType> createEmaidType(EMAIDType value) {
        return new JAXBElement<EMAIDType>(_eMAID_QNAME, EMAIDType.class, null, value);
    }

    @XmlElementDecl(namespace = "urn:iso:15118:2:2013:MsgBody", name = "DHpublickey")
    public static JAXBElement<DiffieHellmanPublickeyType> createDhPublicKeyType(DiffieHellmanPublickeyType value) {
        return new JAXBElement<DiffieHellmanPublickeyType>(_DHpublickey_QNAME, DiffieHellmanPublickeyType.class, null, value);
    }

    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "SignedInfo")
    public static JAXBElement<SignedInfoType> createSignedInfoType(SignedInfoType value) {
        return new JAXBElement<SignedInfoType>(_SignedInfo_QNAME, SignedInfoType.class, null, value);
    }

    @XmlElementDecl(namespace = "urn:iso:15118:2:2013:MsgBody", name = "CertificateInstallationRes", substitutionHeadNamespace = "urn:iso:15118:2:2013:MsgBody", substitutionHeadName = "BodyElement")
    public static JAXBElement<CertificateInstallationResType> createCertificateInstallationRes(CertificateInstallationResType value) {
        return new JAXBElement<CertificateInstallationResType>(_CertificateInstallationRes_QNAME, CertificateInstallationResType.class, null, value);
    }

    @XmlElementDecl(namespace = "urn:iso:15118:2:2013:MsgBody", name = "CertificateInstallationReq", substitutionHeadNamespace = "urn:iso:15118:2:2013:MsgBody", substitutionHeadName = "BodyElement")
    public static JAXBElement<CertificateInstallationReqType> createCertificateInstallationReq(CertificateInstallationReqType value) {
        return new JAXBElement<CertificateInstallationReqType>(_CertificateInstallationReq_QNAME, CertificateInstallationReqType.class, null, value);
    }

    @XmlElementDecl(namespace = "urn:iso:15118:2:2013:MsgBody", name = "CertificateUpdateRes", substitutionHeadNamespace = "urn:iso:15118:2:2013:MsgBody", substitutionHeadName = "BodyElement")
    public static JAXBElement<CertificateUpdateResType> createCertificateUpdateRes(CertificateUpdateResType value) {
        return new JAXBElement<CertificateUpdateResType>(_CertificateUpdateRes_QNAME, CertificateUpdateResType.class, null, value);
    }

    @XmlElementDecl(namespace = "urn:iso:15118:2:2013:MsgBody", name = "CertificateUpdateReq", substitutionHeadNamespace = "urn:iso:15118:2:2013:MsgBody", substitutionHeadName = "BodyElement")
    public static JAXBElement<CertificateUpdateReqType> createCertificateUpdateReq(CertificateUpdateReqType value) {
        return new JAXBElement<CertificateUpdateReqType>(_CertificateUpdateReq_QNAME, CertificateUpdateReqType.class, null, value);
    }

    @XmlElementDecl(namespace = "http://www.w3.org/2000/09/xmldsig#", name = "Signature")
    public static JAXBElement<SignatureType> createSignature(SignatureType value) {
        return new JAXBElement<SignatureType>(_Signature_QNAME, SignatureType.class, null, value);
    }

}
