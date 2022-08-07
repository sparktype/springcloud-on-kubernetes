package io.sparktype.stock.api;

import io.sparktype.stock.repository.Stock;
import io.sparktype.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class StockController {

  private final StockService service;

  @GetMapping("/{id}")
  public ResponseEntity<Stock> detail(@PathVariable Long id) {
    return ResponseEntity.ok(service.detail(id));
  }

  @PostMapping("/")
  public ResponseEntity<Stock> create(@RequestBody Stock request) {
    var created = service.create(request);

    return ResponseEntity.created(
        MvcUriComponentsBuilder.fromController(getClass())
            .path("/{id}")
            .buildAndExpand(created.getId())
            .toUri()
    ).body(created);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Stock> modify(@PathVariable Long id, @RequestBody Stock request) {
    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequestUri().build(id))
        .body(service.modify(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> remove(@PathVariable Long id) {
    service.remove(id);
    return ResponseEntity.noContent().build();
  }

}
