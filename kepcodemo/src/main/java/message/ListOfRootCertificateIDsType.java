package message;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für ListOfRootCertificateIDsType complex type.
 *
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 *
 * <pre>
 * &lt;complexType name="ListOfRootCertificateIDsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RootCertificateID" type="{http://www.w3.org/2000/09/xmldsig#}X509IssuerSerialType" maxOccurs="20"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListOfRootCertificateIDsType", propOrder = {
        "rootCertificateID"
})
public class ListOfRootCertificateIDsType {

    @XmlElement(name = "RootCertificateID", required = true, namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    protected List<X509IssuerSerialType> rootCertificateID;

    /**
     * Gets the value of the rootCertificateID property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rootCertificateID property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRootCertificateID().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link X509IssuerSerialType }
     *
     *
     */
    public List<X509IssuerSerialType> getRootCertificateID() {
        if (rootCertificateID == null) {
            rootCertificateID = new ArrayList<X509IssuerSerialType>();
        }
        return this.rootCertificateID;
    }
    public void setRootCertificateID(List<X509IssuerSerialType> rootCertificateID) {
        this.rootCertificateID = rootCertificateID;
    }
}
