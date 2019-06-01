package clustercamp.user.service;

import clustercamp.base.dto.UserDTO;
import clustercamp.base.exception.Exceptions;
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

  @HystrixCommand(fallbackMethod = "detail_")
  public UserDTO detail(String userId) {
    return repository.findByUserId(userId)
      .map(User::to)
      .orElseThrow(Exceptions::notFound);
  }

  @Transactional
  @HystrixCommand(fallbackMethod = "defaultFallback")
  public UserDTO create(UserDTO dto) {
    return repository.save(User.of(dto)).to();
  }


  @Transactional
  @HystrixCommand(fallbackMethod = "defaultFallback")
  public UserDTO modify(Long id, UserDTO dto) {
    return repository.findById(id)
      .map(user -> repository.save(user.from(dto)).to())
      .orElseThrow(Exceptions::notFound);
  }

  @Transactional
  @HystrixCommand(fallbackMethod = "defaultFallback")
  public void remove(Long id) {
    repository.deleteById(id);
  }

}
