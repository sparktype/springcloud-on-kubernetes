package clustercamp.user.repository;

import clustercamp.user.domain.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRxRepository extends ReactiveCrudRepository<User, Long> {
  Mono<User> findByUserId(String userId);
}
