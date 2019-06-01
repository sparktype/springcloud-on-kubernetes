package clustercamp.sale.api;


import clustercamp.base.dto.SaleDTO;
import clustercamp.sale.service.SaleService;
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
@RequestMapping("/sale")
@RequiredArgsConstructor
public class SaleController {

  private final SaleService service;

  @GetMapping("/{id}")
  public ResponseEntity<SaleDTO> detail(@PathVariable Long id) {
    return ResponseEntity.ok(service.detail(id));
  }

  @PostMapping
  public ResponseEntity<SaleDTO> create(@RequestBody SaleDTO dto) {
    var created = service.create(dto);

    return ResponseEntity.created(MvcUriComponentsBuilder.fromController(getClass())
      .path("/{id}")
      .buildAndExpand(created.getId())
      .toUri()
    ).body(created);
  }

  @PutMapping("/{id}")
  public ResponseEntity<SaleDTO> modify(@PathVariable Long id, @RequestBody SaleDTO dto) {
    var modified = service.modify(id, dto);

    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequestUri()
      .build(modified.getId())
    ).body(modified);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity remove(@PathVariable Long id) {
    service.remove(id);
    return ResponseEntity.noContent().build();
  }
}
