package io.sparktype.commons.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class Exceptions {

  public static HttpNotFoundException notFound() {
    return new HttpNotFoundException();
  }


  @ResponseStatus(HttpStatus.NOT_FOUND)
  public static class HttpNotFoundException extends RuntimeException {

  }
}
