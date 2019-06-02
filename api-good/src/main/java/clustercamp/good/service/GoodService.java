package clustercamp.good.service;

import clustercamp.base.dto.GoodDTO;
import clustercamp.base.exception.Exceptions;
import clustercamp.base.exception.HttpNotFoundException;
import clustercamp.good.repository.Good;
import clustercamp.good.repository.GoodRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class GoodService {

  private final GoodRepository repository;

  @HystrixCommand(commandKey = "good.detail", fallbackMethod = "_detail",
    ignoreExceptions = HttpNotFoundException.class)
  public GoodDTO detail(Long id) {
    return repository.findById(id)
      .map(Good::to)
      .orElseThrow(Exceptions::notFound);
  }

  public GoodDTO _detail(Long id) {
    return GoodDTO.of(id);
  }

  @Transactional
  @HystrixCommand(commandKey = "good.create")
  public GoodDTO create(GoodDTO dto) {
    return repository.save(Good.of(dto)).to();
  }

  @Transactional
  @HystrixCommand(commandKey = "good.modify", ignoreExceptions = HttpNotFoundException.class)
  public GoodDTO modify(Long id, GoodDTO dto) {
    return repository.findById(id)
      .map(good -> repository.save(good.from(dto)).to())
      .orElseThrow(Exceptions::notFound);
  }

  @Transactional
  @HystrixCommand(commandKey = "good.delete")
  public void remove(Long id) {
    repository.deleteById(id);
  }

}
