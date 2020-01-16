package com.pentasecurity.cpo.mo.model;

import com.google.gson.annotations.SerializedName;

public class ContractVerifyResponse implements java.io.Serializable {

    private static final long serialVersionUID = 5807519388147748448L;

    private ResultCode result;

    @SerializedName("verifyResult")
    private ContractVerifyResult verifyResult;

    public ContractVerifyResponse() {
    }

    public ContractVerifyResponse(ResultCode result) {
        this.result = result;
    }

    public ResultCode getResult() {
        return result;
    }

    public void setResult(ResultCode result) {
        this.result = result;
    }

    public ContractVerifyResult getVerifyResult() {
        return verifyResult;
    }

    public void setVerifyResult(ContractVerifyResult verifyResult) {
        this.verifyResult = verifyResult;
    }

}
