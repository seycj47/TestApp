package message;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für X509IssuerSerialType complex type.
 *
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 *
 * <pre>
 * &lt;complexType name="X509IssuerSerialType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="X509IssuerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="X509SerialNumber" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "X509IssuerSerialType", namespace = "http://www.w3.org/2000/09/xmldsig#", propOrder = {
        "x509IssuerName",
        "x509SerialNumber"
})
public class X509IssuerSerialType {

    @XmlElement(name = "X509IssuerName", required = true, namespace = "http://www.w3.org/2000/09/xmldsig#")
    protected String x509IssuerName;
    @XmlElement(name = "X509SerialNumber", required = true, namespace = "http://www.w3.org/2000/09/xmldsig#")
    protected BigInteger x509SerialNumber;

    /**
     * Ruft den Wert der x509IssuerName-Eigenschaft ab.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getX509IssuerName() {
        return x509IssuerName;
    }

    /**
     * Legt den Wert der x509IssuerName-Eigenschaft fest.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setX509IssuerName(String value) {
        this.x509IssuerName = value;
    }

    /**
     * Ruft den Wert der x509SerialNumber-Eigenschaft ab.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    public BigInteger getX509SerialNumber() {
        return x509SerialNumber;
    }

    /**
     * Legt den Wert der x509SerialNumber-Eigenschaft fest.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    public void setX509SerialNumber(BigInteger value) {
        this.x509SerialNumber = value;
    }

}
