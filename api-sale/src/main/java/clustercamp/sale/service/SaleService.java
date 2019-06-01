package clustercamp.sale.service;

import clustercamp.base.dto.SaleDTO;
import clustercamp.base.exception.Exceptions;
import clustercamp.sale.repository.Sale;
import clustercamp.sale.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class SaleService {

  private final SaleRepository repository;

  public SaleDTO detail(Long id) {
    return repository.findById(id)
      .map(Sale::to)
      .orElseThrow(Exceptions::notFound);
  }

  @Transactional
  public SaleDTO create(SaleDTO dto) {
    return repository.save(Sale.of(dto)).to();
  }


  @Transactional
  public SaleDTO modify(Long id, SaleDTO dto) {
    return repository.findById(id)
      .map(sale -> repository.save(sale.from(dto)).to())
      .orElseThrow(Exceptions::notFound);
  }

  @Transactional
  public void remove(Long id) {
    repository.deleteById(id);
  }

}
