package clustercamp.sale.service;

import io.sparktype.commons.exception.Exceptions;
import clustercamp.sale.repository.Sale;
import clustercamp.sale.repository.SaleRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class SaleService {

  private final SaleRepository repository;

  @HystrixCommand(commandKey = "sale.detail", fallbackMethod = "_detail",
    ignoreExceptions = HttpNotFoundException.class)
  public Sale detail(Long id) {
    return repository.findById(id)
      .orElseThrow(Exceptions::notFound);
  }

  public Sale _detail(Long id) {
    return Sale.of(id);
  }

  @Transactional
  @HystrixCommand(commandKey = "sale.create")
  public Sale create(Sale request) {
    return repository.save(request);
  }

  @Transactional
  @HystrixCommand(commandKey = "sale.modify", ignoreExceptions = HttpNotFoundException.class)
  public Sale modify(Long id, Sale request) {
    return repository.findById(id)
      .map(sale -> repository.save(sale.from(request)))
      .orElseThrow(Exceptions::notFound);
  }

  @Transactional
  @HystrixCommand(commandKey = "sale.delete")
  public void remove(Long id) {
    repository.deleteById(id);
  }

}
