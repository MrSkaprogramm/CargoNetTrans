package com.androidcargo.spring.service.impl;

import com.androidcargo.spring.models.order.Order;
import com.androidcargo.spring.models.user.Mover;
import com.androidcargo.spring.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.androidcargo.spring.repository.MoverRepository;
import com.androidcargo.spring.service.MoverServiceInterface;

import java.util.List;
import java.util.Optional;

@Service
public class MoverService implements MoverServiceInterface {
  private final MoverRepository moverRepository;
  private final OrderRepository orderRepository;

  @Autowired
  public MoverService(MoverRepository moverRepository, OrderRepository orderRepository) {
    this.moverRepository = moverRepository;
    this.orderRepository = orderRepository;
  }

  /*@Override
  public void changeServicesList() {
    // TODO Auto-generated method stub

  }*/

  @Override
  public void takeToWork(Order order, Mover mover) {
    List<Mover> movers = order.getMovers();
    movers.add(mover);
    order.setMovers(movers);

    orderRepository.save(order);
  }

  /*@Override
  public void addWorkToList() {
    // TODO Auto-generated method stub

  }*/

  @Override
  public List<Order> getMoverOrders(Integer moverId) {
    Mover mover = moverRepository.findById(moverId).orElse(null);

    if (mover == null) {
      throw new IllegalArgumentException("Mover not found");
    }

    return orderRepository.findByMover(mover);
  }

  @Override
  public Mover getMover(int id) {
    Mover mover = null;
    Optional<Mover> optionalMover = moverRepository.findById(id);
    if (optionalMover.isPresent()) {
      mover = optionalMover.get();
    } else {
      throw new IllegalArgumentException("Car not found");
    }
    return mover;
  }

  @Override
  public List<Mover> getAllMovers() {
    return moverRepository.findAll();
  }
}
