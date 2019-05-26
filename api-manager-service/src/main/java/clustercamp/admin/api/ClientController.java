package clustercamp.admin.api;

import clustercamp.admin.service.ClientService;
import clustercamp.base.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/client")
@RequiredArgsConstructor
public class ClientController {

  private final ClientService service;

  @GetMapping
  public ResponseEntity<ClientDto> detail(@RequestParam(value = "clientId") String clientId) {
    return ResponseEntity.ok(service.detail(clientId));
  }

  @PostMapping
  public ResponseEntity<ClientDto> create(@RequestBody ClientDto request) {
    var clientDto = service.create(request);

    return ResponseEntity.created(
      MvcUriComponentsBuilder.fromController(getClass())
        .path("/{id}")
        .buildAndExpand(clientDto.getId()).toUri()
    ).body(clientDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ClientDto> modify(@PathVariable Long id, @RequestBody ClientDto request) {
    var clientDto = service.modify(id, request);

    return ResponseEntity.created(
      ServletUriComponentsBuilder.fromCurrentRequestUri().build(clientDto.getId())
    ).body(clientDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> remove(@PathVariable Long id) {
    service.remove(id);
    return ResponseEntity.noContent().build();
  }


}
