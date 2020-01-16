package com.pentasecurity.cpo.mo.model;

public class ChargeSalesRequest implements java.io.Serializable {

    private static final long serialVersionUID = -1841954401196484536L;

    // @JsonFormat(pattern = "[a-fA-F]{2}\\-?[0-9a-fA-F]{3}\\-?[0-9a-fA-F]{9}(\\-?[0-9X])?")
    private String emaid;

    private String sessionId;

    private EvChargeEndMsg evChargeEndMsg;

    private String signature;

    private String seccCert;

    private String subca2Cert;

    private String subca1Cert;

    public ChargeSalesRequest() {
    }

    public ChargeSalesRequest(String emaid, String sessionId, EvChargeEndMsg evChargeEndMsg, String signature, String seccCert, String subca2Cert, String subca1Cert) {
        this.emaid = emaid;
        this.sessionId = sessionId;
        this.evChargeEndMsg = evChargeEndMsg;
        this.signature = signature;
        this.seccCert = seccCert;
        this.subca2Cert = subca2Cert;
        this.subca1Cert = subca1Cert;
    }

    public String getEmaid() {
        return emaid;
    }

    public void setEmaid(String emaid) {
        this.emaid = emaid;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public EvChargeEndMsg getEvChargeEndMsg() {
        return evChargeEndMsg;
    }

    public void setEvChargeEndMsg(EvChargeEndMsg evChargeEndMsg) {
        this.evChargeEndMsg = evChargeEndMsg;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSeccCert() {
        return seccCert;
    }

    public void setSeccCert(String seccCert) {
        this.seccCert = seccCert;
    }

    public String getSubca2Cert() {
        return subca2Cert;
    }

    public void setSubca2Cert(String subca2Cert) {
        this.subca2Cert = subca2Cert;
    }

    public String getSubca1Cert() {
        return subca1Cert;
    }

    public void setSubca1Cert(String subca1Cert) {
        this.subca1Cert = subca1Cert;
    }
}
