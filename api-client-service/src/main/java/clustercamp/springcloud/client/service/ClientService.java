package clustercamp.springcloud.client.service;

import clustercamp.springcloud.base.dto.ClientDto;
import clustercamp.springcloud.client.repository.ClientRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

  private final ClientMapper mapper;
  private final ClientRepository repository;

  @HystrixCommand(fallbackMethod = "defaultFallback")
  public ClientDto detail(String clientId) {
    return repository.findByClientId(clientId)
      .map(mapper::convert)
      .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
  }

  @Transactional
  @HystrixCommand(fallbackMethod = "defaultFallback")
  public ClientDto create(ClientDto clientDto) {
    var client = mapper.convert(clientDto);
    return mapper.convert(repository.save(client));
  }

  @Transactional
  @HystrixCommand(fallbackMethod = "defaultFallback")
  public ClientDto modify(Long id, ClientDto clientDto) {
    return repository.findById(id)
      .map(c->{
        c.setClientId(clientDto.getClientId());
        return mapper.convert(repository.save(c));
      })
      .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
  }

  @Transactional
  @HystrixCommand
  public void remove(Long id) {
    repository.deleteById(id);
  }

  public ClientDto defaultFallback() {
    return new ClientDto();
  }

}
