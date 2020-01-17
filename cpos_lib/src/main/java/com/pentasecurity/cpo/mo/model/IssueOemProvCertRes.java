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

  public String getProvCert() {
    return provCert;
  }

  public IssueOemProvCertRes(String id, String pcid, String provCert, String userId, String pfxCert, String subjectDn, String notBefore, String notAfter, String serial, String version, String sigAlgo, String issuerDn, String status) {
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
}
