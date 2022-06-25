package io.sparktype.product.service;

import io.sparktype.commons.exception.Exceptions;
import io.sparktype.product.repository.Product;
import io.sparktype.product.repository.ProductRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository repository;

  public Product detail(Long id) {
    return repository.findById(id)
        .orElseThrow(Exceptions::notFound);
  }

  public Product _detail(Long id) {
    return Product.of(id);
  }

  @Transactional
  public Product create(Product request) {
    return repository.save(request);
  }

  @Transactional
  public Product modify(Long id, Product request) {
    return repository.findById(id)
        .map(good -> repository.save(good.from(request)))
        .orElseThrow(Exceptions::notFound);
  }

  @Transactional
  public void remove(Long id) {
    repository.deleteById(id);
  }

}
