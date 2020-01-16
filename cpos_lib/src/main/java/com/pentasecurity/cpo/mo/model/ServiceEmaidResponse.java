package com.pentasecurity.cpo.mo.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ServiceEmaidResponse implements java.io.Serializable {

    private static final long serialVersionUID = -4700127002685557256L;

    public ServiceEmaidResponse() {
    }

    public ServiceEmaidResponse(ResultCode result, List<EmaidInfo> emaidList) {
        this.result = result;
        this.emaidList = emaidList;
    }

    private ResultCode result;

    public static class EmaidInfo {
        
        // @JsonFormat(pattern = "[a-fA-F]{2}\\-?[0-9a-fA-F]{3}\\-?[0-9a-fA-F]{9}(\\-?[0-9X])?")
        private String emaid;

        private Integer status;

        public EmaidInfo() {
        }

        public EmaidInfo(String emaid, Integer status) {
            this.emaid = emaid;
            this.status = status;
        }

        public String getEmaid() {
            return emaid;
        }

        public void setEmaid(String emaid) {
            this.emaid = emaid;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }
    }

    @SerializedName("emaidList")
    private List<EmaidInfo> emaidList;

    public ResultCode getResult() {
        return result;
    }

    public void setResult(ResultCode result) {
        this.result = result;
    }

    public List<EmaidInfo> getEmaidList() {
        return emaidList;
    }

    public void setEmaidList(List<EmaidInfo> emaidList) {
        this.emaidList = emaidList;
    }

}
