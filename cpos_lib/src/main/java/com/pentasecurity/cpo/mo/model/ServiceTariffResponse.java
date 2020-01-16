package com.pentasecurity.cpo.mo.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ServiceTariffResponse implements java.io.Serializable {

    private static final long serialVersionUID = -6628889979796510685L;

    public ServiceTariffResponse() {
    }

    public ServiceTariffResponse(ResultCode result, List<TariffInfo> tariffList) {
        this.result = result;
        this.tariffList = tariffList;
    }

    private ResultCode result;

    public static class TariffInfo {

        public TariffInfo() {
        }

        public TariffInfo(String tariff, String signature) {
            this.tariff = tariff;
            this.signature = signature;
        }

        // @JsonFormat(pattern = "[A-Fa-f0-9+/=]+")
        private String tariff;

        // @JsonFormat(pattern = "[A-Fa-f0-9+/=]+")
        private String signature;

        public String getTariff() {
            return tariff;
        }

        public void setTariff(String tariff) {
            this.tariff = tariff;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }
    }

    @SerializedName("tariffList")
    private List<TariffInfo> tariffList;

    public ResultCode getResult() {
        return result;
    }

    public void setResult(ResultCode result) {
        this.result = result;
    }

    public List<TariffInfo> getTariffList() {
        return tariffList;
    }

    public void setTariffList(List<TariffInfo> tariffList) {
        this.tariffList = tariffList;
    }

}
