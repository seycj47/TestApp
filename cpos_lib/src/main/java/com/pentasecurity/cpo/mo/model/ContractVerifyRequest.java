package com.pentasecurity.cpo.mo.model;

public class ContractVerifyRequest implements java.io.Serializable {

    private static final long serialVersionUID = -5215253971843639063L;

    // @JsonFormat(pattern = "[a-fA-F]{2}\\-?[0-9a-fA-F]{3}\\-?[0-9a-fA-F]{9}(\\-?[0-9X])?")
    private String emaid;
    
    private String ocspReq;
    
    private ContractVerifyServiceCode serviceCode;

    public ContractVerifyRequest() {
    }

    public ContractVerifyRequest(String emaid, String ocspReq, ContractVerifyServiceCode serviceCode) {
        this.emaid = emaid;
        this.ocspReq = ocspReq;
        this.serviceCode = serviceCode;
    }

    public String getEmaid() {
        return emaid;
    }

    public void setEmaid(String emaid) {
        this.emaid = emaid;
    }
    
    public String getOcspReq() {
        return ocspReq;
    }

    public void setOcspReq(String ocspReq) {
        this.ocspReq = ocspReq;
    }
    
    public ContractVerifyServiceCode getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(ContractVerifyServiceCode serviceCode) {
        this.serviceCode = serviceCode;
    }
}
