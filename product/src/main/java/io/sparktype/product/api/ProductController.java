package io.sparktype.product.api;


import io.sparktype.product.repository.Product;
import io.sparktype.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService service;

  @GetMapping("/{id}")
  public ResponseEntity<Product> detail(@PathVariable Long id) {
    return ResponseEntity.ok(service.detail(id));
  }

  @PostMapping("/")
  public ResponseEntity<Product> create(@RequestBody Product request) {
    var created = service.create(request);

    return ResponseEntity.created(
        MvcUriComponentsBuilder.fromController(getClass())
            .path("/{id}")
            .buildAndExpand(created.getId())
            .toUri()
    ).body(created);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> modify(@PathVariable Long id, @RequestBody Product request) {
    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequestUri().build(id))
        .body(service.modify(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> remove(@PathVariable Long id) {
    service.remove(id);
    return ResponseEntity.noContent().build();
  }

}
