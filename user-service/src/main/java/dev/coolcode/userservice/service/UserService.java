package dev.coolcode.userservice.service;

import dev.coolcode.userservice.domain.User;
import dev.coolcode.userservice.repository.UserRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository repository;

  public UserService(UserRepository userRepository) {
    repository = userRepository;
  }

  public Optional<User> detailByUserId(String userId) {
    return repository.findByUserId(userId);
  }

  public Optional<User> detail(Long seq) {
    return repository.findById(seq);
  }

  @Transactional
  public User create(User user) {
    return repository.save(user);
  }

  @Transactional
  public User modify(User user, User request) {
    user.setEmail(request.getEmail());
    return repository.save(user);
  }

  @Transactional
  public void remove(Long id) {
    repository.deleteById(id);
  }

}
