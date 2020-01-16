package message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubCertificatesType", propOrder = {
        "subCertificate"
})
public class SubCertificatesType {

    @XmlElement(name = "Certificate", required = true, namespace = "urn:iso:15118:2:2013:MsgDataTypes")
    protected List<byte[]> subCertificate;

    /**
     * Gets the value of the certificate property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the certificate property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCertificate().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * byte[]
     *
     */
    public List<byte[]> getSubCertificate() {
        if (subCertificate == null) {
            subCertificate = new ArrayList<byte[]>();
        }
        return this.subCertificate;
    }

    public void setSubCertificate(List<byte[]> subCertificate) {
        this.subCertificate = subCertificate;
    }

}
