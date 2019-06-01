package clustercamp.good.service;

import clustercamp.base.dto.GoodDTO;
import clustercamp.base.exception.Exceptions;
import clustercamp.good.repository.Good;
import clustercamp.good.repository.GoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoodService {

  private final GoodRepository repository;

  public GoodDTO detail(Long id) {
    return repository.findById(id)
      .map(Good::to)
      .orElseThrow(Exceptions::notFound);
  }

  public GoodDTO create(GoodDTO goodDTO) {
    return repository.save(Good.of(goodDTO)).to();
  }


  public GoodDTO modify(Long id, GoodDTO goodDTO) {
    return repository.findById(id)
      .map(good -> repository.save(good.from(goodDTO)).to())
      .orElseThrow(Exceptions::notFound);
  }

  public void remove(Long id) {
    repository.deleteById(id);
  }

}
