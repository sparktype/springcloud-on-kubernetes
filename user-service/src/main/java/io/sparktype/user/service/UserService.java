package clustercamp.user.service;

import javax.transaction.Transactional;

import io.sparktype.commons.exception.Exceptions;
import clustercamp.user.repository.User;
import clustercamp.user.repository.UserRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;

  @HystrixCommand(commandKey = "user.detail", fallbackMethod = "_detail", ignoreExceptions =
    HttpNotFoundException.class)
  public User detail(Long id) {
    return repository.findById(id)
      .orElseThrow(Exceptions::notFound);
  }

  public User _detail(Long id) {
    return User.of(id);
  }

  @Transactional
  @HystrixCommand(commandKey = "user.create")
  public User create(User request) {
    return repository.save(request);
  }

  @Transactional
  @HystrixCommand(commandKey = "user.modify", ignoreExceptions = HttpNotFoundException.class)
  public User modify(Long id, User request) {
    return repository.findById(id)
      .map(user -> repository.save(user.from(request)))
      .orElseThrow(Exceptions::notFound);
  }

  @Transactional
  @HystrixCommand(commandKey = "user.delete")
  public void remove(Long id) {
    repository.deleteById(id);
  }

}
