package clustercamp.user.config;

public class ZipkinConfig {

//  @Autowired
//  private EurekaClient eurekaClient;
//
//  @Autowired
//  private ZipkinProperties zipkinProperties;
//
//  @Value("${spring.sleuth.web.skipPattern}")
//  private String skipPattern;
//
//
//  @Bean
//  public ZipkinSpanReporter makeZipkinSpanReporter() {
//    return new ZipkinSpanReporter() {
//      private HttpZipkinSpanReporter delegate;
//      private String baseUrl;
//
//      @Override
//      public void report(Span span) {
//
//        InstanceInfo instance = eurekaClient
//          .getNextServerFromEureka("zipkin", false);
//        if (!(baseUrl != null &amp;&amp;
//        instance.getHomePageUrl().equals(baseUrl))) {
//          baseUrl = instance.getHomePageUrl();
//          delegate = new HttpZipkinSpanReporter(new RestTemplate(),
//            baseUrl, zipkinProperties.getFlushInterval(), spanMetricReporter);
//          if (!span.name.matches(skipPattern)) delegate.report(span);
//        }
//      }
//    }
//  }
}
