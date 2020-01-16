package message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CertificateInstallationRes", namespace = "urn:iso:15118:2:2013:MsgBody", propOrder = {
        "responseCode",
        "saProvisioningCertificateChain",
        "contractSignatureCertChain",
        "contractSignatureEncryptedPrivateKey",
        "dHpublickey",
        "emaid"
})
public class CertificateInstallationResType extends BodyBaseType {

    @XmlElement(name = "ResponseCode", required = true, namespace = "urn:iso:15118:2:2013:MsgBody")
    @XmlSchemaType(name = "string")
    protected String responseCode;

    @XmlElement(name = "SAProvisioningCertificateChain", required = true, namespace = "urn:iso:15118:2:2013:MsgBody")
    protected CertificateChainType saProvisioningCertificateChain;

    @XmlElement(name = "ContractSignatureCertChain", required = true, namespace = "urn:iso:15118:2:2013:MsgBody")
    protected CertificateChainType contractSignatureCertChain;

    @XmlElement(name = "ContractSignatureEncryptedPrivateKey", required = true, namespace = "urn:iso:15118:2:2013:MsgBody")
    protected ContractSignatureEncryptedPrivateKeyType contractSignatureEncryptedPrivateKey;

    @XmlElement(name = "DHpublickey", required = true, namespace = "urn:iso:15118:2:2013:MsgBody")
    protected DiffieHellmanPublickeyType dHpublickey;

    @XmlElement(name = "eMAID", required = true, namespace = "urn:iso:15118:2:2013:MsgBody")
    protected EMAIDType emaid;

    /**
     * Ruft den Wert der responseCode-Eigenschaft ab.
     *
     * @return
     *     possible object is
     *     {@link ResponseCodeType }
     *
     */
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * Legt den Wert der responseCode-Eigenschaft fest.
     *
     * @param value
     *     allowed object is
     *     {@link ResponseCodeType }
     *
     */
    public void setResponseCode(String value) {
        this.responseCode = value;
    }

    /**
     * Ruft den Wert der saProvisioningCertificateChain-Eigenschaft ab.
     *
     * @return
     *     possible object is
     *     {@link CertificateChainType }
     *
     */
    public CertificateChainType getSAProvisioningCertificateChain() {
        return saProvisioningCertificateChain;
    }

    /**
     * Legt den Wert der saProvisioningCertificateChain-Eigenschaft fest.
     *
     * @param value
     *     allowed object is
     *     {@link CertificateChainType }
     *
     */
    public void setSAProvisioningCertificateChain(CertificateChainType value) {
        this.saProvisioningCertificateChain = value;
    }

    /**
     * Ruft den Wert der contractSignatureCertChain-Eigenschaft ab.
     *
     * @return
     *     possible object is
     *     {@link CertificateChainType }
     *
     */
    public CertificateChainType getContractSignatureCertChain() {
        return contractSignatureCertChain;
    }

    /**
     * Legt den Wert der contractSignatureCertChain-Eigenschaft fest.
     *
     * @param value
     *     allowed object is
     *     {@link CertificateChainType }
     *
     */
    public void setContractSignatureCertChain(CertificateChainType value) {
        this.contractSignatureCertChain = value;
    }

    /**
     * Ruft den Wert der contractSignatureEncryptedPrivateKey-Eigenschaft ab.
     *
     * @return
     *     possible object is
     *     {@link ContractSignatureEncryptedPrivateKeyType }
     *
     */
    public ContractSignatureEncryptedPrivateKeyType getContractSignatureEncryptedPrivateKey() {
        return contractSignatureEncryptedPrivateKey;
    }

    /**
     * Legt den Wert der contractSignatureEncryptedPrivateKey-Eigenschaft fest.
     *
     * @param value
     *     allowed object is
     *     {@link ContractSignatureEncryptedPrivateKeyType }
     *
     */
    public void setContractSignatureEncryptedPrivateKey(ContractSignatureEncryptedPrivateKeyType value) {
        this.contractSignatureEncryptedPrivateKey = value;
    }

    /**
     * Ruft den Wert der dHpublickey-Eigenschaft ab.
     *
     * @return
     *     possible object is
     *     {@link DiffieHellmanPublickeyType }
     *
     */
    public DiffieHellmanPublickeyType getDHpublickey() {
        return dHpublickey;
    }

    /**
     * Legt den Wert der dHpublickey-Eigenschaft fest.
     *
     * @param value
     *     allowed object is
     *     {@link DiffieHellmanPublickeyType }
     *
     */
    public void setDHpublickey(DiffieHellmanPublickeyType value) {
        this.dHpublickey = value;
    }

    /**
     * Ruft den Wert der emaid-Eigenschaft ab.
     *
     * @return
     *     possible object is
     *     {@link EMAIDType }
     *
     */
    public EMAIDType getEMAID() {
        return emaid;
    }

    /**
     * Legt den Wert der emaid-Eigenschaft fest.
     *
     * @param value
     *     allowed object is
     *     {@link EMAIDType }
     *
     */
    public void setEMAID(EMAIDType value) {
        this.emaid = value;
    }

}
