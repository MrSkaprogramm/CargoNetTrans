package com.androidcargo.spring.service;

import com.androidcargo.spring.models.order.Order;
import com.androidcargo.spring.models.order.OrderStatus;
import com.androidcargo.spring.models.user.Client;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderServiceInterface {

  public BigDecimal calculateDriverFreightQuantity(Order order);

  public BigDecimal calculateMoverFreightQuantity(Order order);

  public void makeOrder(Client client, String description, boolean isDowntownWork, boolean isElevatorDelivery, boolean isHeavyLoad, BigDecimal floorsQuantity);

  public BigDecimal calculateOrderPrice(Order order);

  public long calculateOrderTime(LocalDateTime beginTime, LocalDateTime finishTime);

  public LocalDateTime detectOrderTime();

  public Order changeOrderStatus(Order order, OrderStatus orderStatus);

  public List<Order> getAllOrders();

  public Order getOrder(int id);

}
