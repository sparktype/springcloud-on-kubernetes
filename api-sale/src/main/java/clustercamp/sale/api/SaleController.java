package clustercamp.sale.api;

import clustercamp.sale.repository.Sale;
import clustercamp.sale.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
public class SaleController {

  private final SaleService service;

  @GetMapping("/{id}")
  public ResponseEntity<Sale> detail(@PathVariable Long id) {
    return ResponseEntity.ok(service.detail(id));
  }

  @PostMapping
  public ResponseEntity<Sale> create(@RequestBody Sale request) {
    var created = service.create(request);
    return ResponseEntity.created(MvcUriComponentsBuilder.fromController(getClass())
      .path("/{id}")
      .buildAndExpand(created.getId())
      .toUri()
    ).body(created);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Sale> modify(@PathVariable Long id, @RequestBody Sale request) {
    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequestUri().build(id))
      .body(service.modify(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity remove(@PathVariable Long id) {
    service.remove(id);
    return ResponseEntity.noContent().build();
  }
}
