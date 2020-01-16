package message;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java-Klasse für CertificateInstallationReqType complex type.
 *
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 *
 * <pre>
 * &lt;complexType name="CertificateInstallationReqType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:iso:15118:2:2013:MsgBody}BodyBaseType">
 *       &lt;sequence>
 *         &lt;element name="OEMProvisioningCert" type="{urn:iso:15118:2:2013:MsgDataTypes}certificateType"/>
 *         &lt;element name="ListOfRootCertificateIDs" type="{urn:iso:15118:2:2013:MsgDataTypes}ListOfRootCertificateIDsType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Id" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CertificateInstallationReqType", namespace = "urn:iso:15118:2:2013:MsgBody", propOrder = {
        "oemProvisioningCert",
        "listOfRootCertificateIDs"
})
public class CertificateInstallationReqType
        extends BodyBaseType
{

    @XmlElement(name = "OEMProvisioningCert", required = true,  namespace = "urn:iso:15118:2:2013:MsgBody")
    protected byte[] oemProvisioningCert;

    @XmlElement(name = "ListOfRootCertificateIDs", required = true, namespace = "urn:iso:15118:2:2013:MsgBody")
    protected ListOfRootCertificateIDsType listOfRootCertificateIDs;

    @XmlAttribute(name = "Id", required = true, namespace = "urn:iso:15118:2:2013:MsgBody")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    /**
     * Ruft den Wert der oemProvisioningCert-Eigenschaft ab.
     *
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getOEMProvisioningCert() {
        return oemProvisioningCert;
    }

    /**
     * Legt den Wert der oemProvisioningCert-Eigenschaft fest.
     *
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setOEMProvisioningCert(byte[] value) {
        this.oemProvisioningCert = value;
    }

    /**
     * Ruft den Wert der listOfRootCertificateIDs-Eigenschaft ab.
     *
     * @return
     *     possible object is
     *     {@link ListOfRootCertificateIDsType }
     *
     */
    public ListOfRootCertificateIDsType getListOfRootCertificateIDs() {
        return listOfRootCertificateIDs;
    }

    /**
     * Legt den Wert der listOfRootCertificateIDs-Eigenschaft fest.
     *
     * @param value
     *     allowed object is
     *     {@link ListOfRootCertificateIDsType }
     *
     */
    public void setListOfRootCertificateIDs(ListOfRootCertificateIDsType value) {
        this.listOfRootCertificateIDs = value;
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
