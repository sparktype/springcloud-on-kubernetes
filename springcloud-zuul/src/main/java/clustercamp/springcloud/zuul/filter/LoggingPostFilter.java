package clustercamp.springcloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class LoggingPostFilter extends ZuulFilter {

  private static final int ORDER = 999;

  @Override
  public String filterType() {
    return FilterType.POST.toString();
  }

  @Override
  public int filterOrder() {
    return LoggingPostFilter.ORDER;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() throws ZuulException {

    RequestContext context = RequestContext.getCurrentContext();

    try {
      RequestLogging logging = (RequestLogging) context.get(ContextType.REQUEST_INFO.toString());
      logging.setStatus(context.getResponse().getStatus());
      logging.setFinalTime(System.currentTimeMillis());
      log.debug(logging.toString());
    } catch (ClassCastException exception) {
      throw new ZuulException(exception.getCause(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
        exception.getMessage());
    }
    return null;
  }
}
