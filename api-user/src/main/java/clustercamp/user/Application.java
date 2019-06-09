package clustercamp.user;

import clustercamp.user.repository.User;
import clustercamp.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableJpaAuditing
@SpringCloudApplication
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  @ConditionalOnProperty(name = "app.initial.data", havingValue = "true")
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
      user.setCreateBy("INITIAL");
      repository.save(user);
    };
  }
}
