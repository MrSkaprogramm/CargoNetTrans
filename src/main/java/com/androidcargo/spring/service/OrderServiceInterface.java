package com.androidcargo.spring.service;

import com.androidcargo.spring.models.order.Order;
import com.androidcargo.spring.models.order.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderServiceInterface {

  public void beginOrder();

  void makeOrder();

  public BigDecimal calculateOrderPrice(Order order);

  public long calculateOrderTime(LocalDateTime beginTime, LocalDateTime finishTime);

  public LocalDateTime detectOrderTime();

  public Order changeOrderStatus(Order order, OrderStatus orderStatus);

  public List<Order> getAllOrders();

}
