package io.sparktype.stock.service;

import io.sparktype.commons.exception.Exceptions;
import io.sparktype.stock.repository.Stock;
import io.sparktype.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class StockService {

  private final StockRepository repository;

  public Stock detail(Long id) {
    return repository.findById(id)
        .orElseThrow(Exceptions::notFound);
  }

  public Stock _detail(Long id) {
    return Stock.of(id);
  }

  @Transactional
  public Stock create(Stock request) {
    return repository.save(request);
  }

  @Transactional
  public Stock modify(Long id, Stock request) {
    return repository.findById(id)
        .map(good -> repository.save(good.from(request)))
        .orElseThrow(Exceptions::notFound);
  }

  @Transactional
  public void remove(Long id) {
    repository.deleteById(id);
  }

}
