//package clustercamp.springcloud.zuul.filter;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//
//import javax.servlet.http.HttpServletRequest;
//
//public class LoggingPreFilter extends ZuulFilter {
//
//  private static final int ORDER = 0;
//
//  @Override
//  public String filterType() {
//    return FilterType.PRE.toString();
//  }
//
//  @Override
//  public int filterOrder() {
//    return LoggingPreFilter.ORDER;
//  }
//
//  @Override
//  public boolean shouldFilter() {
//    return true;
//  }
//
//  @Override
//  public Object run() throws ZuulException {
//    RequestContext context = RequestContext.getCurrentContext();
//    HttpServletRequest request = context.getRequest();
//
//    context.set(ContextType.REQUEST_INFO.toString(), RequestLogging.builder()
//      .uri(request.getRequestURI())
//      .startTime(System.currentTimeMillis())
//      .build());
//    return null;
//  }
//}
