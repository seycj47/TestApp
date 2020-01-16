package com.pentasecurity.cpo.mo.model;

public class ChargeSalesResponse implements java.io.Serializable {

    private static final long serialVersionUID = 1129656558447309373L;

    private ResponseResult result;

    public ChargeSalesResponse() {
    }

    public ChargeSalesResponse(ResponseResult result) {
        this.result = result;
    }

    public ResponseResult getResult() {
        return result;
    }

    public void setResult(ResponseResult result) {
        this.result = result;
    }

}
