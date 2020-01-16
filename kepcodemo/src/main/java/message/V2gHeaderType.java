package message;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Header", namespace = "urn:iso:15118:2:2013:MsgDef",
        propOrder = {"sessionId", "signature"})
public class V2gHeaderType {

    @XmlElement(name = "SessionID", required = true, namespace = "urn:iso:15118:2:2013:MsgHeader")
    @XmlSchemaType(name = "string")
    protected String sessionId;

    @XmlElement(name = "Signature", required = true, namespace = "http://www.w3.org/2000/09/xmldsig#")
    @XmlSchemaType(name = "string")
    protected SignatureType signature;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public SignatureType getSignature() {
        return signature;
    }

    public void setSignature(SignatureType signature) {
        this.signature = signature;
    }
}
