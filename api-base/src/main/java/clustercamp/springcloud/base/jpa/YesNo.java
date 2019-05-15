package clustercamp.springcloud.base.jpa;

public enum YesNo {
  YES("Y"), NO("N");

  private String value;

  YesNo(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public boolean booleanValue() {
    return value.equals("Y");
  }

}
