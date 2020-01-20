package com.pentasecurity.cpo.mo.model;

public class IssueOemProvCertRes {
	private String id;
	private String pcid;
	private String provCert;
	private String userId;
	private String pfxCert;
	private String subjectDn;
	private String notBefore;
	private String notAfter;
	private String serial;
	private String version;
	private String sigAlgo;
	private String issuerDn;
	private String status;

	public IssueOemProvCertRes(String id, String pcid, String provCert, String userId, String pfxCert, String subjectDn,
			String notBefore, String notAfter, String serial, String version, String sigAlgo, String issuerDn,
			String status) {
		this.id = id;
		this.pcid = pcid;
		this.provCert = provCert;
		this.userId = userId;
		this.pfxCert = pfxCert;
		this.subjectDn = subjectDn;
		this.notBefore = notBefore;
		this.notAfter = notAfter;
		this.serial = serial;
		this.version = version;
		this.sigAlgo = sigAlgo;
		this.issuerDn = issuerDn;
		this.status = status;
	}

	
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPcid() {
		return pcid;
	}


	public void setPcid(String pcid) {
		this.pcid = pcid;
	}


	public String getProvCert() {
		return provCert;
	}


	public void setProvCert(String provCert) {
		this.provCert = provCert;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getPfxCert() {
		return pfxCert;
	}


	public void setPfxCert(String pfxCert) {
		this.pfxCert = pfxCert;
	}


	public String getSubjectDn() {
		return subjectDn;
	}


	public void setSubjectDn(String subjectDn) {
		this.subjectDn = subjectDn;
	}


	public String getNotBefore() {
		return notBefore;
	}


	public void setNotBefore(String notBefore) {
		this.notBefore = notBefore;
	}


	public String getNotAfter() {
		return notAfter;
	}


	public void setNotAfter(String notAfter) {
		this.notAfter = notAfter;
	}


	public String getSerial() {
		return serial;
	}


	public void setSerial(String serial) {
		this.serial = serial;
	}


	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}


	public String getSigAlgo() {
		return sigAlgo;
	}


	public void setSigAlgo(String sigAlgo) {
		this.sigAlgo = sigAlgo;
	}


	public String getIssuerDn() {
		return issuerDn;
	}


	public void setIssuerDn(String issuerDn) {
		this.issuerDn = issuerDn;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "IssueOemProvCertRes [id=" + id + ", pcid=" + pcid + ", provCert=" + provCert + ", userId=" + userId
				+ ", pfxCert=" + pfxCert + ", subjectDn=" + subjectDn + ", notBefore=" + notBefore + ", notAfter="
				+ notAfter + ", serial=" + serial + ", version=" + version + ", sigAlgo=" + sigAlgo + ", issuerDn="
				+ issuerDn + ", status=" + status + "]";
	}
}
