package com.pentasecurity.cpo.mo.model;

public class IssueCpoCertRequest {
	private String cn;

  public void setCn(String cn) {
    this.cn = cn;
  }

  public String getCn() {
    return cn;
  }

  public IssueCpoCertRequest(String cn) {
		super();
		this.cn = cn;
	}	
}
