package clustercamp.sale.service;

import clustercamp.base.dto.SaleDTO;
import clustercamp.base.exception.Exceptions;
import clustercamp.base.exception.HttpNotFoundException;
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
  public SaleDTO detail(Long id) {
    return repository.findById(id)
      .map(Sale::to)
      .orElseThrow(Exceptions::notFound);
  }

  public SaleDTO _detail(Long id) {
    return SaleDTO.of(id);
  }

  @Transactional
  @HystrixCommand(commandKey = "sale.create")
  public SaleDTO create(SaleDTO dto) {
    return repository.save(Sale.of(dto)).to();
  }

  @Transactional
  @HystrixCommand(commandKey = "sale.modify", ignoreExceptions = HttpNotFoundException.class)
  public SaleDTO modify(Long id, SaleDTO dto) {
    return repository.findById(id)
      .map(sale -> repository.save(sale.from(dto)).to())
      .orElseThrow(Exceptions::notFound);
  }

  @Transactional
  @HystrixCommand(commandKey = "sale.delete")
  public void remove(Long id) {
    repository.deleteById(id);
  }

}
