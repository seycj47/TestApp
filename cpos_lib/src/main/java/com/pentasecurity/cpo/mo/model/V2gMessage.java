package com.pentasecurity.cpo.mo.model;

public class V2gMessage {

	private String v2gMessage;

	public V2gMessage(String v2gMessage) {
		super();
		this.v2gMessage = v2gMessage;
	}

	public String getV2gMessage() {
		return v2gMessage;
	}

	public void setV2gMessage(String v2gMessage) {
		this.v2gMessage = v2gMessage;
	}

	@Override
	public String toString() {
		return "V2gMessage [v2gMessage=" + v2gMessage + "]";
	}

}
