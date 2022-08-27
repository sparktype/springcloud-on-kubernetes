package io.sparktype.purchase.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends ReactiveCrudRepository<Sale, Long> {

}
