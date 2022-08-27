package io.sparktype.purchase.service;

import io.sparktype.purchase.repository.Sale;
import io.sparktype.purchase.repository.SaleItemRepository;
import io.sparktype.purchase.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class SaleService {

  private final SaleRepository repository;

  private final SaleItemRepository itemRepository;

  public Mono<Sale> detail(Long id) {
    return repository.findById(id);
  }

  public Sale _detail(Long id) {
    return Sale.of(id);
  }

  @Transactional

  public Mono<Sale> create(Sale request) {
    return repository.save(request);
  }

  @Transactional
  public Mono<Sale> modify(Long id, Sale request) {
    return repository.findById(id)
        .flatMap(s -> repository.save(s.from(request)));
  }

  @Transactional
  public void remove(Long id) {
    repository.deleteById(id);
  }

}
