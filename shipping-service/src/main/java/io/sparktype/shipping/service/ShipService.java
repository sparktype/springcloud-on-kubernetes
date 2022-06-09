package io.sparktype.shipping.service;

import io.sparktype.commons.exception.Exceptions;
import io.sparktype.shipping.repository.Ship;
import io.sparktype.shipping.repository.ShipRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShipService {

  private final ShipRepository repository;

  public Ship detail(Long id) {
    return repository.findById(id)
        .orElseThrow(Exceptions::notFound);
  }

  public Ship _detail(Long id) {
    return Ship.of(id);
  }

  @Transactional
  public Ship create(Ship request) {
    return repository.save(request);
  }

  @Transactional
  public Ship modify(Long id, Ship request) {
    return repository.findById(id)
        .map(ship -> repository.save(ship.from(request)))
        .orElseThrow(Exceptions::notFound);
  }

  @Transactional
  public void remove(Long id) {
    repository.deleteById(id);
  }

}
