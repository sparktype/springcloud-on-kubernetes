package clustercamp.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import clustercamp.security.repository.User;
import clustercamp.security.repository.UserRepository;

@Component
public class BaseUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return repository.findByUsername(username)
      .map(User::to)
      .orElseThrow(() -> new UsernameNotFoundException(String.format("{username} not found", username)));
  }
}
