package com.pentasecurity.cpo.mo.model;

public class Pkcs10Req {
  private byte[] pkcs10Data;
  private String cn;


  public Pkcs10Req(byte[] pkcs10Data, String cn) {
    this.pkcs10Data = pkcs10Data;
    this.cn = cn;
  }
}
