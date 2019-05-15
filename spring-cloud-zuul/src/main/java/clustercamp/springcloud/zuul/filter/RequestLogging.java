package clustercamp.springcloud.zuul.filter;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
class RequestLogging {

  private String uri;

  private String user;

  private int status;

  private long startTime;

  private long finalTime;

}
