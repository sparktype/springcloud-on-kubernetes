package clustercamp.base.exception;

public class Exceptions {

  public static HttpNotFoundException notFound() {
    return new HttpNotFoundException();
  }
}
