package models;

public class IssueTlsCertRequest {
  private String cn;

  public void setCn(String cn) {
    this.cn = cn;
  }

  public String getCn() {
    return cn;
  }

  public IssueTlsCertRequest(String cn) {
    super();
    this.cn = cn;
  }
}