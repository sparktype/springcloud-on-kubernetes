package io.sparktype.purchase.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleItemRepository extends ReactiveCrudRepository<SaleItem, Long> {

}
