package clustercamp.springcloud.user.repository;

import clustercamp.springcloud.user.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  Optional<User> findByUserId(String userId);
}
