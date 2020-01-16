package com.pentasecurity.cpo.mo.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class GetTariffResponse {
	private ResultCode result;
    
	@SerializedName("tariffList")
    private List<TariffInfo> tariffList;

	private String signature;	
	
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

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
    
	
	public GetTariffResponse() {
    }

	public GetTariffResponse(ResultCode result, List<TariffInfo> tariffList, String signature) {
		super();
		this.result = result;
		this.tariffList = tariffList;
		this.signature = signature;
	}
}
