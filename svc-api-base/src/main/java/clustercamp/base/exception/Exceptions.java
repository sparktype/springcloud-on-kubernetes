package clustercamp.base.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;

public class Exceptions {

  public static HttpStatusCodeException notFound() {
    return new HttpClientErrorException(HttpStatus.NOT_FOUND);
  }
}
