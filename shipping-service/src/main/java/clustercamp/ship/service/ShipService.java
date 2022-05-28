package clustercamp.ship.service;

import io.sparktype.commons.exception.Exceptions;
import clustercamp.ship.repository.Ship;
import clustercamp.ship.repository.ShipRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ShipService {

  private final ShipRepository repository;

  @HystrixCommand(commandKey = "ship.detail", fallbackMethod = "_detail",
    ignoreExceptions = HttpNotFoundException.class)
  public Ship detail(Long id) {
    return repository.findById(id)
      .orElseThrow(Exceptions::notFound);
  }

  public Ship _detail(Long id) {
    return Ship.of(id);
  }

  @Transactional
  @HystrixCommand(commandKey = "ship.create")
  public Ship create(Ship request) {
    return repository.save(request);
  }

  @Transactional
  @HystrixCommand(commandKey = "ship.modify", ignoreExceptions = HttpNotFoundException.class)
  public Ship modify(Long id, Ship request) {
    return repository.findById(id)
      .map(ship -> repository.save(ship.from(request)))
      .orElseThrow(Exceptions::notFound);
  }

  @Transactional
  @HystrixCommand(commandKey = "ship.delete")
  public void remove(Long id) {
    repository.deleteById(id);
  }

}
