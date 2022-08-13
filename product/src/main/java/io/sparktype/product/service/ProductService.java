package io.sparktype.product.service;

import io.sparktype.commons.exception.Exceptions;
import io.sparktype.product.repository.Product;
import io.sparktype.product.repository.ProductRepository;
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

  public Product create(Product request) {
    return repository.save(request);
  }

  public Product modify(Long id, Product request) {
    return repository.findById(id)
        .map(good -> repository.save(good.from(request)))
        .orElseThrow(Exceptions::notFound);
  }

  public void remove(Long id) {
    repository.deleteById(id);
  }

}
