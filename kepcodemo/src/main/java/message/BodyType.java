package message;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BodyType", namespace = "urn:iso:15118:2:2013:MsgBody",
        propOrder = {"bodyElement"})
public class BodyType {
    @XmlElementRef(name = "BodyElement", namespace = "urn:iso:15118:2:2013:MsgBody", type = JAXBElement.class, required = false)
    JAXBElement<? extends BodyBaseType> bodyElement = null;

    public JAXBElement<? extends BodyBaseType> getBodyElement() {
        return bodyElement;
    }

    public void setBodyElement(JAXBElement<? extends BodyBaseType> bodyElement) {
        this.bodyElement = bodyElement;
    }
}
