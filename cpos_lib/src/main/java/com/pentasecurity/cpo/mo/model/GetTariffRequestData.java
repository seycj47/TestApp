package com.pentasecurity.cpo.mo.model;

import java.util.List;

public class GetTariffRequestData {
	private  String emaid;
	private  String chargeType;
	private  List<Integer> tupleIdList;
	private  List<PmaxSchedule> pmaxScheduleList;
	
	public GetTariffRequestData(String emaid, String chargeType, List<Integer> tupleIdList, List<PmaxSchedule> pmaxScheduleList) {
		super();
		this.emaid = emaid;
		this.chargeType = chargeType;
		this.tupleIdList = tupleIdList;
		this.pmaxScheduleList = pmaxScheduleList;
	}
	public String getEmaid() {
		return emaid;
	}
	public void setEmaid(String emaid) {
		this.emaid = emaid;
	}
	public String getChargeType() {
		return chargeType;
	}
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	public List<Integer> getTupleIdList() {
		return tupleIdList;
	}
	public void setTupleIdList(List<Integer> tupleIdList) {
		this.tupleIdList = tupleIdList;
	}
	public List<PmaxSchedule> getPmaxScheduleList() {
		return pmaxScheduleList;
	}
	public void setPmaxScheduleList(List<PmaxSchedule> pmaxScheduleList) {
		this.pmaxScheduleList = pmaxScheduleList;
	}
	
	
}

