package com.androidcargo.spring.service.impl;

import com.androidcargo.spring.models.car.Car;
import com.androidcargo.spring.models.order.Order;
import com.androidcargo.spring.models.order.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.androidcargo.spring.repository.OrderRepository;
import com.androidcargo.spring.service.OrderServiceInterface;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderServiceInterface {
  private final OrderRepository orderRepository;
  private final DataService dataService;

  @Autowired
  public OrderService(OrderRepository orderRepository, DataService dataService) {
    this.orderRepository = orderRepository;
    this.dataService = dataService;
  }

  @Override
  public void beginOrder() {
    Order order = new Order();
  }

  @Override
  public void makeOrder() {
    Order order = null;
    orderRepository.save(order);
  }

  //@Override
  public void calculateOrderPrice() {
    // TODO Auto-generated method stub

  }

  @Override
  public long calculateOrderTime(LocalDateTime beginTime, LocalDateTime finishTime) {
    if (beginTime != null && finishTime != null && finishTime.isAfter(beginTime)) {
      return java.time.Duration.between(beginTime, finishTime).toMinutes();
    } else {
      throw new IllegalArgumentException("Неверные временные данные.");
    }
  }

  @Override
  public LocalDateTime detectOrderTime() {
    LocalDateTime orderTime = LocalDateTime.now();
    return orderTime;
  }

  //@Override
  public void changeOrderStatus(Order order, OrderStatus) {

  }

  public Car getCar(int id) {
    Car car = null;
    Optional<Car> optionalCar = carRepository.findById(id);
    if (optionalCar.isPresent()) {
      car = optionalCar.get();
    } else {
      throw new IllegalArgumentException("Car not found");
    }
    return car;
  }

  @Override
  public List<Order> getAllOrders() {
    return orderRepository.findAll();
  }

}
