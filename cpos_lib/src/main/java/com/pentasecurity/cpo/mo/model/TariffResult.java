package com.pentasecurity.cpo.mo.model;

import java.util.List;

public class TariffResult {
	@Override
	public String toString() {
		return "TariffResult [tupleId=" + tupleId + ", tariff=" + tariff + ", signiture=" + signiture + ", moSubCa2="
				+ moSubCa2 + "]";
	}
	private List<Integer> tupleId;
	private List<String> tariff;
	private String signiture;
	private String moSubCa2;
	
	public TariffResult(List<Integer> tupleId, List<String> tariff, String signiture, String moSubCa2) {
		super();
		this.tupleId = tupleId;
		this.tariff = tariff;
		this.signiture = signiture;
		this.moSubCa2 = moSubCa2;
	}
	
	
	public TariffResult() {
		super();
	}

	public List<Integer> getTupleId() {
		return tupleId;
	}
	public void setTupleId(List<Integer> tupleId) {
		this.tupleId = tupleId;
	}
	public List<String> getTariff() {
		return tariff;
	}
	public void setTariff(List<String> tariff) {
		this.tariff = tariff;
	}
	public String getSigniture() {
		return signiture;
	}
	public void setSigniture(String signiture) {
		this.signiture = signiture;
	}
	public String getMoSubCa2() {
		return moSubCa2;
	}
	public void setMoSubCa2(String moSubCa2) {
		this.moSubCa2 = moSubCa2;
	}

	
}
