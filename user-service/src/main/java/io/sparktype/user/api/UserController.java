package clustercamp.user.api;

import clustercamp.user.repository.User;
import clustercamp.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final UserService service;

  @GetMapping("/{id}")
  public ResponseEntity<User> detail(@PathVariable Long id) {
    return ResponseEntity.ok(service.detail(id));
  }

  @PostMapping("/")
  public ResponseEntity<User> create(@RequestBody User request) {
    var userDto = service.create(request);

    return ResponseEntity.created(
        MvcUriComponentsBuilder.fromController(getClass())
            .path("/{id}")
            .buildAndExpand(userDto.getId()).toUri()
    ).body(userDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> modify(@PathVariable Long id, @RequestBody User request) {
    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequestUri().build(id))
        .body(service.modify(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> remove(@PathVariable Long id) {
    service.remove(id);
    return ResponseEntity.noContent().build();
  }

}
