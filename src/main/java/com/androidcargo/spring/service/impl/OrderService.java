package com.androidcargo.spring.service.impl;

import com.androidcargo.spring.models.order.Order;
import com.androidcargo.spring.models.order.OrderStatus;
import com.androidcargo.spring.models.user.Client;
import com.androidcargo.spring.models.user.Driver;
import com.androidcargo.spring.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.androidcargo.spring.repository.OrderRepository;
import com.androidcargo.spring.service.OrderServiceInterface;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderServiceInterface {
  private final OrderRepository orderRepository;
  private final DriverRepository driverRepository;

  @Autowired
  public OrderService(OrderRepository orderRepository, DriverRepository driverRepository) {
    this.orderRepository = orderRepository;
      this.driverRepository = driverRepository;
  }

  @Override
  public BigDecimal calculateDriverFreightQuantity(Order order) {
    /*long orderTime = calculateOrderTime(order.getActualStartTime(), order.getActualEndTime());
    BigDecimal freight = order.getDriver().getCars().getFirst().getDriverWork().getFreight();

    BigDecimal bigDecimalOrderTime = new BigDecimal(orderTime);

    BigDecimal orderFreight = bigDecimalOrderTime.divide(freight, RoundingMode.HALF_UP);*/


    return null;
  }

  @Override
  public BigDecimal calculateMoverFreightQuantity(Order order) {
    /*long orderTime = calculateOrderTime(order.getActualStartTime(), order.getActualEndTime());
    BigDecimal freight = order.getDriver().getCars().getFirst().getDriverWork().getFreight();

    BigDecimal bigDecimalOrderTime = new BigDecimal(orderTime);

    BigDecimal orderFreight = bigDecimalOrderTime.divide(freight, RoundingMode.HALF_UP);*/


    return null;
  }

  @Override
  public void makeOrder(Client client, String description, boolean isDowntownWork, boolean isElevatorDelivery, boolean isHeavyLoad, BigDecimal floorsQuantity) {
    Order order = new Order();
    order.setOrderStatus(OrderStatus.SEARCH);
    //order.setClient(client);
    order.setDescription(description);
    order.setDowntownWork(isDowntownWork);
    order.setElevatorDelivery(isElevatorDelivery);
    order.setHeavyLoad(isHeavyLoad);
    order.setFloorsQuantity(floorsQuantity);

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
    /*BigDecimal driverPrice = null;
    Driver driver = order.getDriver();
    DriverWork driverWork = driver.getCars().getFirst().getDriverWork();

    if (order.isDowntownWork()) {
      driverPrice = driverWork.getFreight().multiply(driverWork.getPricePerFreightHour()
              .multiply(order.getFreightQuantity()));
              //.add(driverWork.getPricePerDowntownKm().multiply(BigDecimal.valueOf(order.getLocation().getDistance()))); //В городе цена = фрахт

    } else {
      driverPrice = driverWork.getFreight().multiply(driverWork.getPricePerFreightHour())
              .multiply(order.getFreightQuantity()); //За городом цена = фрахт + километры за городом
    }*/

    return null;
  }

  public BigDecimal calculateMoversPrice(Order order) {
    /*BigDecimal moverPrice = null;
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
    }*/

    return null;
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

  @Override
  public Order getOrder(int id) {
    Order order = null;
    Optional<Order> optionalOrder = orderRepository.findById(id);
    if (optionalOrder.isPresent()) {
      order = optionalOrder.get();
    } else {
      throw new IllegalArgumentException("Car not found");
    }
    return order;
  }

  @Override
  public List<Order> getAvailableOrders() {
    return orderRepository.findByOrderStatus(OrderStatus.SEARCH);
  }

  @Override
  public boolean acceptOrder(Integer orderId, String driverPhone) {
    Optional<Order> orderOpt = orderRepository.findById(orderId);
    Optional<Driver> driverOpt = driverRepository.findByPhoneNumber(driverPhone);

    if (orderOpt.isPresent() && driverOpt.isPresent()) {
      Order order = orderOpt.get();
      Driver driver = driverOpt.get();

      if (order.getOrderStatus() == OrderStatus.SEARCH) {
        //order.setDriver(driver);
        order.setOrderStatus(OrderStatus.IN_PROCESS);
        orderRepository.save(order);
        return true;
      }
    }
    return false;
  }

}
