package io.sparktype.users.service;

import io.sparktype.commons.exception.Exceptions;
import io.sparktype.users.repository.Users;
import io.sparktype.users.repository.UsersRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

  private final UsersRepository repository;


  public Users detail(Long id) {
    return repository.findById(id)
        .orElseThrow(Exceptions::notFound);
  }

  public Users _detail(Long id) {
    return Users.of(id);
  }

  @Transactional
  public Users create(Users request) {
    return repository.save(request);
  }

  @Transactional
  public Users modify(Long id, Users request) {
    return repository.findById(id)
        .map(u -> repository.save(u.from(request)))
        .orElseThrow(Exceptions::notFound);
  }

  @Transactional
  public void remove(Long id) {
    repository.deleteById(id);
  }

}
