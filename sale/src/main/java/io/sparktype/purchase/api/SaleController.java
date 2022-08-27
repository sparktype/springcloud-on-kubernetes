package io.sparktype.purchase.api;

import io.sparktype.purchase.repository.Sale;
import io.sparktype.purchase.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;


@RestController
@RequestMapping("/sale")
@RequiredArgsConstructor
public class SaleController {

  private final SaleService service;

  @GetMapping("/{id}")
  public Mono<Sale> detail(@PathVariable Long id) {
    return service.detail(id);
  }

  @PostMapping("/")
  public Mono<EntityModel<Sale>> create(@RequestBody Sale request) {
    var controller = methodOn(getClass());
    Mono<Link> selfLink = linkTo(controller.create(request)).withSelfRel().toMono();
    return Mono.zip(service.create(request), selfLink).map(o -> EntityModel.of(o.getT1(), Links.of(o.getT2())));
  }

  @PutMapping("/{id}")
  public Mono<EntityModel<Sale>> modify(@PathVariable Long id, @RequestBody Sale request) {
    var controller = methodOn(getClass());
    Mono<Link> selfLink = linkTo(controller.modify(id, request)).withSelfRel().toMono();
    return Mono.zip(service.modify(id, request), selfLink).map(o -> EntityModel.of(o.getT1(), Links.of(o.getT2())));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity remove(@PathVariable Long id) {
    service.remove(id);
    return ResponseEntity.noContent().build();
  }
}
