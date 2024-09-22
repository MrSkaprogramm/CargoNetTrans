package com.androidcargo.spring.service.impl;

import com.androidcargo.spring.models.order.Order;
import com.androidcargo.spring.models.order.OrderStatus;
import com.androidcargo.spring.models.user.Driver;
import com.androidcargo.spring.models.user.Mover;
import com.androidcargo.spring.models.work.DriverWork;
import com.androidcargo.spring.models.work.MoverWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.androidcargo.spring.repository.OrderRepository;
import com.androidcargo.spring.service.OrderServiceInterface;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

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

  @Override
  public BigDecimal calculateOrderPrice(Order order) {
    BigDecimal driverPrice = calculateDriverPrice(order);
    BigDecimal moverPrice = calculateMoversPrice(order);

    BigDecimal totalOrderPrice = driverPrice.add(moverPrice);

    return totalOrderPrice;
  }

  public BigDecimal calculateDriverPrice(Order order) {
    BigDecimal driverPrice = null;
    Driver driver = order.getDriver();
    DriverWork driverWork = driver.getCars().getFirst().getDriverWork();

    if (order.isDowntownWork()) {
      driverPrice = driverWork.getFreight().multiply(driverWork.getPricePerFreightHour()
              .multiply(order.getFreightQuantity()))
              .add(driverWork.getPricePerDowntownKm().multiply(BigDecimal.valueOf(order.getLocation().getDistance()))); //В городе цена = фрахт

    } else {
      driverPrice = driverWork.getFreight().multiply(driverWork.getPricePerFreightHour())
              .multiply(order.getFreightQuantity()); //За городом цена = фрахт + километры за городом
    }

    return driverPrice;
  }

  public BigDecimal calculateMoversPrice(Order order) {
    BigDecimal moverPrice = null;
    List<Mover> movers = order.getMovers();

    MoverWork moverWork = movers.getFirst().getMoverWorksList().getFirst();

    moverPrice = moverWork.getFreight().multiply(moverWork.getPricePerFreightHour())
            .multiply(order.getFreightQuantity()); //В городе цена = фрахт

    if (order.isHeavyLoad()) {
      moverPrice = moverWork.getFreight().multiply(moverWork.getPricePerHeavyLoadFreightHour())
              .multiply(order.getFreightQuantity()); //При высокой нашрузке используется другая цена фрахта
    }

    if (order.isDowntownWork()) {
      moverPrice = moverPrice.add(moverWork.getPricePerDeliveryHour()); //При работе за городом цена = цена + цена доставки за город
    }

    if (order.isElevatorDelivery()) {
      moverPrice = moverPrice.add(moverWork.getPricePerFloorLifting().multiply(order.getFloorsQuantity())); //При подъёме без лифта цена = цена + цена подъёма на каждый этаж
    }

    return moverPrice;
  }

  @Override
  public long calculateOrderTime(LocalDateTime beginTime, LocalDateTime finishTime) {
    if (beginTime != null && finishTime != null && finishTime.isAfter(beginTime)) {
      return Duration.between(beginTime, finishTime).toMinutes();
    } else {
      throw new IllegalArgumentException("Неверные временные данные.");
    }
  }

  @Override
  public LocalDateTime detectOrderTime() {
    LocalDateTime orderTime = LocalDateTime.now();
    return orderTime;
  }

  @Override
  public Order changeOrderStatus(Order order, OrderStatus orderStatus) {
    order.setOrderStatus(orderStatus);
    return order;
  }

  @Override
  public List<Order> getAllOrders() {
    return orderRepository.findAll();
  }

}
