package message;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CertificateChainType", propOrder = {
        "certificate",
        "subCertificates"
})
public class CertificateChainType {

    @XmlElement(name = "Certificate", required = true, namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    protected byte[] certificate;
    @XmlElement(name = "SubCertificates", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    protected SubCertificatesType subCertificates;
    @XmlAttribute(name = "Id", namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Ruft den Wert der certificate-Eigenschaft ab.
     *
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getCertificate() {
        return certificate;
    }

    /**
     * Legt den Wert der certificate-Eigenschaft fest.
     *
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setCertificate(byte[] value) {
        this.certificate = value;
    }

    /**
     * Ruft den Wert der subCertificates-Eigenschaft ab.
     *
     * @return
     *     possible object is
     *     {@link SubCertificatesType }
     *
     */
    public SubCertificatesType getSubCertificates() {
        return subCertificates;
    }

    /**
     * Legt den Wert der subCertificates-Eigenschaft fest.
     *
     * @param value
     *     allowed object is
     *     {@link SubCertificatesType }
     *
     */
    public void setSubCertificates(SubCertificatesType value) {
        this.subCertificates = value;
    }

    /**
     * Ruft den Wert der id-Eigenschaft ab.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getId() {
        return id;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setId(String value) {
        this.id = value;
    }

}
