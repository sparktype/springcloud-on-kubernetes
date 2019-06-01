package clustercamp.order.service;

import clustercamp.base.dto.OrderDTO;
import clustercamp.base.exception.Exceptions;
import clustercamp.order.repository.Order;
import clustercamp.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository repository;

  public OrderDTO detail(Long id) {
    return repository.findById(id)
      .map(Order::to)
      .orElseThrow(Exceptions::notFound);
  }

  @Transactional
  public OrderDTO create(OrderDTO dto) {
    return repository.save(Order.of(dto)).to();
  }


  @Transactional
  public OrderDTO modify(Long id, OrderDTO dto) {
    return repository.findById(id)
      .map(user -> repository.save(user.from(dto)).to())
      .orElseThrow(Exceptions::notFound);
  }

  @Transactional
  public void remove(Long id) {
    repository.deleteById(id);
  }

}
