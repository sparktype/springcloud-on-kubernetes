package io.sparktype.shipping.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipRepository extends CrudRepository<Ship, Long> {

}
