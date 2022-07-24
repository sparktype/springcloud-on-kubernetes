package io.sparktype.user;

import io.sparktype.user.repository.User;
import io.sparktype.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableJpaAuditing
@SpringBootApplication
public class Starter {

  public static void main(String[] args) {
    SpringApplication.run(Starter.class, args);
  }

  @Bean
  public CommandLineRunner loadData(UserRepository repository, PasswordEncoder encoder) {
    return args -> {
      var user = new User();
      user.setRealname("Leonardo Park");
      user.setUsername("leonardo");
      user.setPassword(encoder.encode("72001827"));
      user.setRoles("ROLE_USER,ROLE_ADMIN");
      user.setAccountNonExpired(true);
      user.setAccountNonLocked(true);
      user.setCredentialsNonExpired(true);
      user.setEnabled(true);
      repository.save(user);
    };
  }
}
