package com.pentasecurity.cpo.mo.model;

public class ResponseResult implements java.io.Serializable {

    private static final long serialVersionUID = 7431038790803196591L;

    public ResponseResult() {
    }

    public ResponseResult(ResultCode code) {
        this.code = code;
    }

    public ResponseResult(ResultCode code, String message) {
        this.code = code;
        this.message = message;
    }

    private ResultCode code;

    private String message;

    public static ResponseResult SUCCESS() {
        return new ResponseResult(ResultCode.SUCCESS);
    }

    public static ResponseResult FAIL(String message) {
        return new ResponseResult(ResultCode.FAIL, message);
    }

    public ResultCode getCode() {
        return code;
    }

    public void setCode(ResultCode code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
