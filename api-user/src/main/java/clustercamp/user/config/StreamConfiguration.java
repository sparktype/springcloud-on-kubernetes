package clustercamp.user.config;

import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
public class StreamConfiguration {

  private static final String ORIGINAL_QUEUE = "so8400in.so8400";

  private static final String DLQ = ORIGINAL_QUEUE + ".dlq";

  private static final String PARKING_LOT = ORIGINAL_QUEUE + ".parkingLot";

  private static final String X_RETRIES_HEADER = "x-retries";

  private static final String DELAY_EXCHANGE = "dlqReRouter";

  private final RabbitTemplate rabbitTemplate;

  @RabbitListener(queues = DLQ)
  public void rePublish(Message failedMessage) {
    Map<String, Object> headers = failedMessage.getMessageProperties().getHeaders();
    Integer retriesHeader = (Integer) headers.get(X_RETRIES_HEADER);
    if (retriesHeader == null) {
      retriesHeader = Integer.valueOf(0);
    }
    if (retriesHeader < 3) {
      headers.put(X_RETRIES_HEADER, retriesHeader + 1);
      headers.put("x-delay", 5000 * retriesHeader);
      rabbitTemplate.send(DELAY_EXCHANGE, ORIGINAL_QUEUE, failedMessage);
    } else {
      rabbitTemplate.send(PARKING_LOT, failedMessage);
    }
  }

  @Bean
  public DirectExchange delayExchange() {
    DirectExchange exchange = new DirectExchange(DELAY_EXCHANGE);
    exchange.setDelayed(true);
    return exchange;
  }

//  @Bean
//  public Binding bindOriginalToDelay() {
//    return BindingBuilder.bind(new Queue(ORIGINAL_QUEUE).to(delayExchange()).with(ORIGINAL_QUEUE);
//  }
//
//  @Bean
//  public Queue parkingLot() {
//    return new Queue(PARKING_LOT);
//  }
}
