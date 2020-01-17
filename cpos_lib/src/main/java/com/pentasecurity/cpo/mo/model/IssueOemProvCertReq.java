package com.pentasecurity.cpo.mo.model;

public class IssueOemProvCertReq {
  private String pcid;

  public String getPcid() {
    return pcid;
  }

  public void setPcid(String pcid) {
    this.pcid = pcid;
  }

  public IssueOemProvCertReq(String pcid) {
    this.pcid = pcid;
  }
}
