package io.sparktype.sales.service;

import io.sparktype.commons.exception.Exceptions;
import io.sparktype.sales.repository.Sale;
import io.sparktype.sales.repository.SaleRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleService {

  private final SaleRepository repository;

  public Sale detail(Long id) {
    return repository.findById(id)
        .orElseThrow(Exceptions::notFound);
  }

  public Sale _detail(Long id) {
    return Sale.of(id);
  }

  @Transactional

  public Sale create(Sale request) {
    return repository.save(request);
  }

  @Transactional
  public Sale modify(Long id, Sale request) {
    return repository.findById(id)
        .map(sale -> repository.save(sale.from(request)))
        .orElseThrow(Exceptions::notFound);
  }

  @Transactional
  public void remove(Long id) {
    repository.deleteById(id);
  }

}
