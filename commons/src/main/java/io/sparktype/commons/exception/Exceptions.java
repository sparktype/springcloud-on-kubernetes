package io.sparktype.commons.exception;

public class Exceptions {

  public static HttpNotFoundException notFound() {
    return new HttpNotFoundException();
  }
}
