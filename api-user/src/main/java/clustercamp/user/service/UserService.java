package clustercamp.user.service;

import clustercamp.base.dto.UserDTO;
import clustercamp.base.exception.Exceptions;
import clustercamp.base.exception.HttpNotFoundException;
import clustercamp.user.repository.User;
import clustercamp.user.repository.UserRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;

  @HystrixCommand(commandKey = "user.detailByName", fallbackMethod = "_detail",
    ignoreExceptions = HttpNotFoundException.class)
  public UserDTO detail(String userName) {
    return repository.findByUserName(userName)
      .map(User::to)
      .orElseThrow(Exceptions::notFound);
  }

  @HystrixCommand(commandKey = "user.detailById", fallbackMethod = "_detail",
    ignoreExceptions = HttpNotFoundException.class)
  public UserDTO detail(Long id) {
    return repository.findById(id)
      .map(User::to)
      .orElseThrow(Exceptions::notFound);
  }

  public UserDTO _detail(Long id) {
    return UserDTO.of(id);
  }

  public UserDTO _detail(String userName) {
    return UserDTO.of(userName);
  }

  @Transactional
  @HystrixCommand(commandKey = "user.create")
  public UserDTO create(UserDTO dto) {
    return repository.save(User.of(dto)).to();
  }

  @Transactional
  @HystrixCommand(commandKey = "user.modify", ignoreExceptions = HttpNotFoundException.class)
  public UserDTO modify(Long id, UserDTO dto) {
    return repository.findById(id)
      .map(user -> repository.save(user.from(dto)).to())
      .orElseThrow(Exceptions::notFound);
  }

  @Transactional
  @HystrixCommand(commandKey = "user.delete")
  public void remove(Long id) {
    repository.deleteById(id);
  }

}
