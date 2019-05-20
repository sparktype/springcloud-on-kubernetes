package clustercamp.user.api;

import clustercamp.base.dto.UserDTO;
import clustercamp.user.service.UserService;
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
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService service;

  @GetMapping
  public ResponseEntity<UserDTO> detail(@RequestParam String userId) {
    return ResponseEntity.ok(service.detail(userId));
  }

  @PostMapping
  public ResponseEntity<UserDTO> create(@RequestBody UserDTO request) {
    var userDto = service.create(request);

    return ResponseEntity.created(
      MvcUriComponentsBuilder.fromController(getClass())
        .path("/{id}")
        .buildAndExpand(userDto.getId()).toUri()
    ).body(userDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserDTO> modify(@PathVariable Long id, @RequestBody UserDTO request) {
    var userDto = service.modify(id, request);

    return ResponseEntity.created(
      ServletUriComponentsBuilder.fromCurrentRequestUri().build(userDto.getId())
    ).body(userDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> remove(@PathVariable Long id) {
    service.remove(id);
    return ResponseEntity.noContent().build();
  }

}
