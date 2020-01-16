package com.pentasecurity.cpo.mo.model;

public class V2gMessagePartitial {
	
	private String signature;
	private String v2gMessage;
	
	
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getV2gMessage() {
		return v2gMessage;
	}
	public void setV2gMessage(String v2gMessage) {
		this.v2gMessage = v2gMessage;
	}
		
	public V2gMessagePartitial(String signature, String v2gMessage) {
		super();
		this.signature = signature;
		this.v2gMessage = v2gMessage;
	}

}
