package com.androidcargo.spring.service;

import com.androidcargo.spring.models.order.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderServiceInterface {

  public void beginOrder();

  void makeOrder();

  public long calculateOrderTime(LocalDateTime beginTime, LocalDateTime finishTime);

  public LocalDateTime detectOrderTime();

  public List<Order> getAllOrders();

}
