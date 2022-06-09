package io.sparktype.user.service;

import io.sparktype.commons.exception.Exceptions;
import io.sparktype.user.repository.User;
import io.sparktype.user.repository.UserRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;


  public User detail(Long id) {
    return repository.findById(id)
        .orElseThrow(Exceptions::notFound);
  }

  public User _detail(Long id) {
    return User.of(id);
  }

  @Transactional
  public User create(User request) {
    return repository.save(request);
  }

  @Transactional
  public User modify(Long id, User request) {
    return repository.findById(id)
        .map(user -> repository.save(user.from(request)))
        .orElseThrow(Exceptions::notFound);
  }

  @Transactional
  public void remove(Long id) {
    repository.deleteById(id);
  }

}
