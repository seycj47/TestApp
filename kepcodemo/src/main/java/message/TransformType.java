package message;


import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;


/**
 * <p>Java-Klasse für TransformType complex type.
 *
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 *
 * <pre>
 * &lt;complexType name="TransformType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;any processContents='lax' namespace='##other'/>
 *         &lt;element name="XPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/choice>
 *       &lt;attribute name="Algorithm" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "TransformType", namespace = "http://www.w3.org/2000/09/xmldsig#", propOrder = {
//        "content"
//})
@XmlType(name = "TransformType", namespace = "http://www.w3.org/2000/09/xmldsig#", propOrder = {
        "algorithm"
})
public class TransformType {

    @XmlAttribute(name = "Algorithm", required = true)
    protected String algorithm = "http://www.w3.org/TR/canonical-exi/";


    /**
     * Ruft den Wert der algorithm-Eigenschaft ab.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAlgorithm() {
        return algorithm;
    }

    /**
     * Legt den Wert der algorithm-Eigenschaft fest.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAlgorithm(String value) {
        this.algorithm = value;
    }

}
