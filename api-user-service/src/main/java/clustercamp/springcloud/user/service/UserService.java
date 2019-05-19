package clustercamp.springcloud.user.service;

import clustercamp.springcloud.base.dto.UserDto;
import clustercamp.springcloud.user.repository.UserRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;

  private final UserMapper mapper;

  @HystrixCommand(fallbackMethod = "detail_")
  public UserDto detail(String userId) {
    return repository.findByUserId(userId)
      .map(mapper::convert)
      .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
  }

  public UserDto detail_(String userId) {
    return new UserDto();
  }

  @Transactional
  @HystrixCommand(fallbackMethod = "defaultFallback")
  public UserDto create(UserDto userDto) {
    var user = mapper.convert(userDto);
    return mapper.convert(repository.save(user));
  }

  @Transactional
  @HystrixCommand(fallbackMethod = "defaultFallback")
  public void remove(Long id) {
    repository.deleteById(id);
  }

  @Transactional
  @HystrixCommand(fallbackMethod = "defaultFallback")
  public UserDto modify(Long id, UserDto userDto) {
    return repository.findById(id)
      .map(c -> {
        c.setUserPass(userDto.getUserPass());
        return mapper.convert(repository.save(c));
      })
      .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
  }
}
