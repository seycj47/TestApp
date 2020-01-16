package message;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "V2G_Message", namespace = "urn:iso:15118:2:2013:MsgDef")
@XmlType(name = "V2G_Message", namespace = "urn:iso:15118:2:2013:MsgDef",
        propOrder = {"header", "body"})
public class V2GMessage {

    @XmlElement(name = "Header", namespace = "urn:iso:15118:2:2013:MsgDef", required = true)
    protected V2gHeaderType header;

    @XmlElement(name = "Body", namespace = "urn:iso:15118:2:2013:MsgDef", required = true)
    protected BodyType body;

    public V2gHeaderType getHeader() {
        return header;
    }

    public void setHeader(V2gHeaderType header) {
        this.header = header;
    }

    public BodyType getBody() {
        return body;
    }

    public void setBody(BodyType body) {
        this.body = body;
    }
}
