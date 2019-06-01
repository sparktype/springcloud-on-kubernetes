package clustercamp.ship.service;

import clustercamp.base.dto.ShipDTO;
import clustercamp.base.exception.Exceptions;
import clustercamp.ship.repository.Ship;
import clustercamp.ship.repository.ShipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class ShipService {

  private final ShipRepository repository;

  public ShipDTO detail(Long id) {
    return repository.findById(id)
      .map(Ship::to)
      .orElseThrow(Exceptions::notFound);
  }

  @Transactional
  public ShipDTO create(ShipDTO dto) {
    return repository.save(Ship.of(dto)).to();
  }


  @Transactional
  public ShipDTO modify(Long id, ShipDTO dto) {
    return repository.findById(id)
      .map(ship -> repository.save(ship.from(dto)).to())
      .orElseThrow(Exceptions::notFound);
  }

  @Transactional
  public void remove(Long id) {
    repository.deleteById(id);
  }

}
