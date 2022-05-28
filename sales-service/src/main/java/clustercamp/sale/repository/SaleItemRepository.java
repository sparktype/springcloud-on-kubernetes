package clustercamp.sale.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleItemRepository extends CrudRepository<SaleItem, Long> {
}
