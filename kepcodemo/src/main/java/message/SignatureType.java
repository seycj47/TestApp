package message;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Java-Klasse für SignatureType complex type.
 *
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 *
 * <pre>
 * &lt;complexType name="SignatureType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}SignedInfo"/>
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}SignatureValue"/>
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}KeyInfo" minOccurs="0"/>
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}Object" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Signature", namespace = "http://www.w3.org/2000/09/xmldsig#")
@XmlType(name = "Signature", namespace = "http://www.w3.org/2000/09/xmldsig#",
        propOrder = {"signedInfo", "signatureValue"})
public class SignatureType {

    @XmlElement(name = "SignedInfo", required = true, namespace = "http://www.w3.org/2000/09/xmldsig#")
    protected SignedInfoType signedInfo;

    @XmlElement(name = "SignatureValue", required = true, namespace = "http://www.w3.org/2000/09/xmldsig#")
    @XmlSchemaType(name = "string")
    protected byte[] signatureValue;

    public SignedInfoType getSignedInfo() {
        return signedInfo;
    }

    public void setSignedInfo(SignedInfoType signedInfo) {
        this.signedInfo = signedInfo;
    }

    public byte[] getSignatureValue() {
        return signatureValue;
    }

    public void setSignatureValue(byte[] signatureValue) {
        this.signatureValue = signatureValue;
    }
}
