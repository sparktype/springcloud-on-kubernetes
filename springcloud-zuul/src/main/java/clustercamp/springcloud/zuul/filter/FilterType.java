package clustercamp.springcloud.zuul.filter;

public enum FilterType {

  PRE("pre"), ROUTE("route"), POST("post");

  private final String value;

  FilterType(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}
