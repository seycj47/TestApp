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
 * <p>Java-Klasse für ReferenceType complex type.
 *
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 *
 * <pre>
 * &lt;complexType name="ReferenceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}Transforms" minOccurs="0"/>
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}DigestMethod"/>
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}DigestValue"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="URI" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="Type" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Reference", namespace = "http://www.w3.org/2000/09/xmldsig#", propOrder = {
        "uri",
        "transforms",
        "digestMethod",
        "digestValue"
})
public class ReferenceType {


    @XmlAttribute(name = "URI")
    @XmlSchemaType(name = "anyURI")
    protected String uri;

    @XmlElement(name = "Transforms", required = true, namespace = "http://www.w3.org/2000/09/xmldsig#")
    protected TransformsType transforms;

    @XmlElement(name = "DigestMethod", required = true, namespace = "http://www.w3.org/2000/09/xmldsig#")
    protected DigestMethodType digestMethod;
    @XmlElement(name = "DigestValue", required = true, namespace = "http://www.w3.org/2000/09/xmldsig#")
    protected byte[] digestValue;

    public ReferenceType() {
        this.digestMethod = new DigestMethodType();
    }

    /**
     * Ruft den Wert der transforms-Eigenschaft ab.
     *
     * @return
     *     possible object is
     *     {@link TransformsType }
     *
     */
    public TransformsType getTransforms() {
        return transforms;
    }

    /**
     * Legt den Wert der transforms-Eigenschaft fest.
     *
     * @param value
     *     allowed object is
     *     {@link TransformsType }
     *
     */
    public void setTransforms(TransformsType value) {
        this.transforms = value;
    }

    /**
     * Ruft den Wert der digestMethod-Eigenschaft ab.
     *
     * @return
     *     possible object is
     *     {@link DigestMethodType }
     *
     */
    public DigestMethodType getDigestMethod() {
        return digestMethod;
    }

    /**
     * Legt den Wert der digestMethod-Eigenschaft fest.
     *
     * @param value
     *     allowed object is
     *     {@link DigestMethodType }
     *
     */
    public void setDigestMethod(DigestMethodType value) {
        this.digestMethod = value;
    }

    /**
     * Ruft den Wert der digestValue-Eigenschaft ab.
     *
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getDigestValue() {
        return digestValue;
    }

    /**
     * Legt den Wert der digestValue-Eigenschaft fest.
     *
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setDigestValue(byte[] value) {
        this.digestValue = value;
    }


    /**
     * Ruft den Wert der uri-Eigenschaft ab.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getURI() {
        return uri;
    }

    /**
     * Legt den Wert der uri-Eigenschaft fest.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setURI(String value) {
        this.uri = value;
    }

}
