package com.pentasecurity.cpo.mo.model;

public class IssueTlsCertResponse {
  private String subjectCN;
  private String serial;
  private  String certificateData;

  public String getSubjectCN() {
    return subjectCN;
  }

  public void setSubjectCN(String subjectCN) {
    this.subjectCN = subjectCN;
  }

  public String getSerial() {
    return serial;
  }

  public void setSerial(String serial) {
    this.serial = serial;
  }

  public String getCertificateData() {
    return certificateData;
  }

  public void setCertificateData(String certificateData) {
    this.certificateData = certificateData;
  }

  public IssueTlsCertResponse(String subjectCN, String serial, String certificateData) {
    this.subjectCN = subjectCN;
    this.serial = serial;
    this.certificateData = certificateData;
  }

  //	private String id;
//	private String seccId;
//	private String provCert;
//	private String userId;
//	private String pfxCert;
//	private String subjectDn;
//	private String notBefore;
//	private String notAfter;
//	private String serial;
//	private String version;
//	private String signAlgo;
//	private String issuerDn;
//	private String status;
//
//	public IssueTlsCertResponse(String id, String seccId, String provCert, String userId, String pfxCert,
//			String subjectDn, String notBefore, String notAfter, String serial, String version, String signAlgo,
//			String issuerDn, String status) {
//		super();
//		this.id = id;
//		this.seccId = seccId;
//		this.provCert = provCert;
//		this.userId = userId;
//		this.pfxCert = pfxCert;
//		this.subjectDn = subjectDn;
//		this.notBefore = notBefore;
//		this.notAfter = notAfter;
//		this.serial = serial;
//		this.version = version;
//		this.signAlgo = signAlgo;
//		this.issuerDn = issuerDn;
//		this.status = status;
//	}
//
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
//	public String getSeccId() {
//		return seccId;
//	}
//	public void setSeccId(String seccId) {
//		this.seccId = seccId;
//	}
//	public String getProvCert() {
//		return provCert;
//	}
//	public void setProvCert(String provCert) {
//		this.provCert = provCert;
//	}
//	public String getUserId() {
//		return userId;
//	}
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}
//	public String getPfxCert() {
//		return pfxCert;
//	}
//	public void setPfxCert(String pfxCert) {
//		this.pfxCert = pfxCert;
//	}
//	public String getSubjectDn() {
//		return subjectDn;
//	}
//	public void setSubjectDn(String subjectDn) {
//		this.subjectDn = subjectDn;
//	}
//	public String getNotBefore() {
//		return notBefore;
//	}
//	public void setNotBefore(String notBefore) {
//		this.notBefore = notBefore;
//	}
//	public String getNotAfter() {
//		return notAfter;
//	}
//	public void setNotAfter(String notAfter) {
//		this.notAfter = notAfter;
//	}
//	public String getSerial() {
//		return serial;
//	}
//	public void setSerial(String serial) {
//		this.serial = serial;
//	}
//	public String getVersion() {
//		return version;
//	}
//	public void setVersion(String version) {
//		this.version = version;
//	}
//	public String getSignAlgo() {
//		return signAlgo;
//	}
//	public void setSignAlgo(String signAlgo) {
//		this.signAlgo = signAlgo;
//	}
//	public String getIssuerDn() {
//		return issuerDn;
//	}
//	public void setIssuerDn(String issuerDn) {
//		this.issuerDn = issuerDn;
//	}
//	public String getStatus() {
//		return status;
//	}
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	@Override
//	public String toString() {
//		return "IssueTlsCertResponse [id=" + id + ", seccId=" + seccId + ", provCert=" + provCert + ", userId=" + userId
//				+ ", pfxCert=" + pfxCert + ", subjectDn=" + subjectDn + ", notBefore=" + notBefore + ", notAfter="
//				+ notAfter + ", serial=" + serial + ", version=" + version + ", signAlgo=" + signAlgo + ", issuerDn="
//				+ issuerDn + ", status=" + status + "]";
//	}
	
}
