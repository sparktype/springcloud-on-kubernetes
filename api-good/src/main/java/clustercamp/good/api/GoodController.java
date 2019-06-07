package clustercamp.good.api;

import clustercamp.good.repository.Good;
import clustercamp.good.service.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/good")
@RequiredArgsConstructor
public class GoodController {

  private final GoodService service;

  @GetMapping("/{id}")
  public ResponseEntity<Good> detail(@PathVariable Long id) {
    return ResponseEntity.ok(service.detail(id));
  }

  @PostMapping
  public ResponseEntity<Good> create(@RequestBody Good request) {
    var created = service.create(request);
    return ResponseEntity.created(
      MvcUriComponentsBuilder.fromController(getClass())
        .path("/{id}").buildAndExpand(created.getId()).toUri()
    ).body(created);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Good> modify(@PathVariable Long id, @RequestBody Good request) {
    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequestUri().build(id))
      .body(service.modify(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity remove(@PathVariable Long id) {
    service.remove(id);
    return ResponseEntity.noContent().build();
  }

}
