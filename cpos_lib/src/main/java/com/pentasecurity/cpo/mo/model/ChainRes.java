package com.pentasecurity.cpo.mo.model;

public class ChainRes {
	private String chain;

	public String getChain() {
		return chain;
	}

	public void setChain(String chain) {
		this.chain = chain;
	}

	public ChainRes(String chain) {
		this.chain = chain;
	}

	@Override
	public String toString() {
		return "ChainRes [chain=" + chain + "]";
	}

}