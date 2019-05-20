package clustercamp.admin.repository;

import clustercamp.admin.domain.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

  Optional<Client> findByClientId(String clientId);
}
