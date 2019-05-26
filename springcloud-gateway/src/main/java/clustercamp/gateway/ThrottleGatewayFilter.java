package clustercamp.gateway;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@Slf4j
@Getter
public class ThrottleGatewayFilter implements GatewayFilter {

  int capacity;

  int refillTokens;

  int refillPeriod;

  TimeUnit refillUnit;

  public ThrottleGatewayFilter setCapacity(int capacity) {
    this.capacity = capacity;
    return this;
  }

  public ThrottleGatewayFilter setRefillTokens(int refillTokens) {
    this.refillTokens = refillTokens;
    return this;
  }

  public ThrottleGatewayFilter setRefillPeriod(int refillPeriod) {
    this.refillPeriod = refillPeriod;
    return this;
  }

  public ThrottleGatewayFilter setRefillUnit(TimeUnit refillUnit) {
    this.refillUnit = refillUnit;
    return this;
  }

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

//    TokenBucket tokenBucket = TokenBuckets.builder().withCapacity(capacity)
//      .withFixedIntervalRefillStrategy(refillTokens, refillPeriod, refillUnit)
//      .build();
//
//    // TODO: get a token bucket for a key
//    log.debug("TokenBucket capacity: " + tokenBucket.getCapacity());
//    boolean consumed = tokenBucket.tryConsume();
//    if (consumed) {
//      return chain.filter(exchange);
//    }
//    exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
//    return exchange.getResponse().setComplete();
    return null;
  }
}
