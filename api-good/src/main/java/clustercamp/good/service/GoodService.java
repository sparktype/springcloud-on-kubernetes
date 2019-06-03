package clustercamp.good.service;

import javax.transaction.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import clustercamp.base.exception.Exceptions;
import clustercamp.base.exception.HttpNotFoundException;
import clustercamp.good.repository.Good;
import clustercamp.good.repository.GoodRepository;

@Service
@RequiredArgsConstructor
public class GoodService {

  private final GoodRepository repository;

  @HystrixCommand(commandKey = "good.detail", fallbackMethod = "_detail",
    ignoreExceptions = HttpNotFoundException.class)
  public Good detail(Long id) {
    return repository.findById(id)
      .orElseThrow(Exceptions::notFound);
  }

  public Good _detail(Long id) {
    return Good.of(id);
  }

  @Transactional
  @HystrixCommand(commandKey = "good.create")
  public Good create(Good request) {
    return repository.save(request);
  }

  @Transactional
  @HystrixCommand(commandKey = "good.modify", ignoreExceptions = HttpNotFoundException.class)
  public Good modify(Long id, Good request) {
    return repository.findById(id)
      .map(good -> repository.save(good.from(request)))
      .orElseThrow(Exceptions::notFound);
  }

  @Transactional
  @HystrixCommand(commandKey = "good.delete")
  public void remove(Long id) {
    repository.deleteById(id);
  }

}
