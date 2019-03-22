package dev.coolcode.userservice.repository;

import dev.coolcode.userservice.domain.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  Optional<User> findByUserId(String userId);
}
