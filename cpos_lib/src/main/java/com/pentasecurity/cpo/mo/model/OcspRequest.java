package com.pentasecurity.cpo.mo.model;

public class OcspRequest {

	private String validCert;
	private String caCert;
	private String ServerIP;
	private String url_OCSP;	
	
	public OcspRequest(String validCert, String caCert, String serverIP, String url_OCSP) {
		super();
		this.validCert = validCert;
		this.caCert = caCert;
		this.ServerIP = serverIP;
		this.url_OCSP = url_OCSP;
	}
	
	public String getValidCert() {
		return validCert;
	}
	public void setValidCert(String validCert) {
		this.validCert = validCert;
	}
	public String getCaCert() {
		return caCert;
	}
	public void setCaCert(String caCert) {
		this.caCert = caCert;
	}
	public String getServerIP() {
		return ServerIP;
	}
	public void setServerIP(String serverIP) {
		this.ServerIP = serverIP;
	}
	public String getUrl_OCSP() {
		return url_OCSP;
	}
	public void setUrl_OCSP(String url_OCSP) {
		this.url_OCSP = url_OCSP;
	}
	
	
}
