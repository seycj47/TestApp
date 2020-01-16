package com.pentasecurity.cpo.mo.model;

public class IssueTlsCertRequest {
	private String seccId;

	public String getSeccId() {
		return seccId;
	}

	public void setSeccId(String seccId) {
		this.seccId = seccId;
	}

	public IssueTlsCertRequest(String seccId) {
		super();
		this.seccId = seccId;
	}	
}
