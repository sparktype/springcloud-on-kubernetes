package clustercamp.ship.service;

import clustercamp.base.dto.ShipDTO;
import clustercamp.base.exception.Exceptions;
import clustercamp.base.exception.HttpNotFoundException;
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
  public ShipDTO detail(Long id) {
    return repository.findById(id)
      .map(Ship::to)
      .orElseThrow(Exceptions::notFound);
  }

  public ShipDTO _detail(Long id) {
    return ShipDTO.of(id);
  }

  @Transactional
  @HystrixCommand(commandKey = "ship.create")
  public ShipDTO create(ShipDTO dto) {
    return repository.save(Ship.of(dto)).to();
  }


  @Transactional
  @HystrixCommand(commandKey = "ship.modify", ignoreExceptions = HttpNotFoundException.class)
  public ShipDTO modify(Long id, ShipDTO dto) {
    return repository.findById(id)
      .map(ship -> repository.save(ship.from(dto)).to())
      .orElseThrow(Exceptions::notFound);
  }

  @Transactional
  @HystrixCommand(commandKey = "ship.delete")
  public void remove(Long id) {
    repository.deleteById(id);
  }

}
