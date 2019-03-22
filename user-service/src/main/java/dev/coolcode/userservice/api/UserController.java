package dev.coolcode.userservice.api;

import dev.coolcode.userservice.domain.User;
import dev.coolcode.userservice.exception.UserNotFoundException;
import dev.coolcode.userservice.service.UserService;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/user")
public class UserController {

  private final UserService service;

  public UserController(UserService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<User> detailUser(@RequestParam String userId) {
    return service.detailByUserId(userId)
        .map(ResponseEntity::ok)
        .orElseThrow(UserNotFoundException::new);
  }

  @PostMapping
  @ResponseBody
  public ResponseEntity<User> createUser(@RequestBody User user) {
    URI uri = MvcUriComponentsBuilder.fromController(getClass())
        .path("/{userName}")
        .buildAndExpand(user.getUserName())
        .toUri();
    return ResponseEntity.created(uri).body(service.create(user));
  }

  @PutMapping("/{id}")
  @ResponseBody
  public ResponseEntity<User> modifyUser(@PathVariable Long id, @RequestBody User user) {
    return service.detail(id)
        .map(u -> ResponseEntity
            .created(URI.create(ServletUriComponentsBuilder.fromCurrentRequest().toString()))
            .body(service.modify(u, user)))
        .orElseThrow(UserNotFoundException::new);
  }

  @DeleteMapping("/{id}")
  @ResponseBody
  public ResponseEntity<String> deleteUser(@PathVariable Long id) {
    service.remove(id);
    return ResponseEntity.noContent().build();
  }

}
