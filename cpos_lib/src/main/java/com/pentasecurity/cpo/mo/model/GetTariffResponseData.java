package com.pentasecurity.cpo.mo.model;

public class GetTariffResponseData {
	
	private ResultCode result;
	private  int tupleId;
	private  String tariff;
	private  String signedValue;
	private  String subca2;
	
	public GetTariffResponseData(ResultCode result, int tupleId, String tariff, String signedValue, String subca2){
		this.tupleId = tupleId;
		this.tariff = tariff;
		this.signedValue = signedValue;
		this.subca2 = subca2;
	}
	
	public ResultCode getResult() {
        return result;
    }

    public void setResult(ResultCode result) {
        this.result = result;
    }
	
	public void setTupleId(int tupleId) {
		this.tupleId = tupleId;
	}
	
	public int getTupleId() {
		return tupleId;
	}
	
	public void setTariff(String tariff) {
		this.tariff = tariff;
	}
	
	public String getTariff() {
		return tariff;
	}	
	
	public void setSignedValue(String signedValue) {
		this.signedValue = signedValue;
	}
	
	public String getSignedValue() {
		return signedValue;
	}
	
	
	public void setsubca2(String  subca2) {
		this.subca2 = subca2;
	}
	
	public String  getSubca2() {
		return subca2;
	}
	
	
	
	
	
}
