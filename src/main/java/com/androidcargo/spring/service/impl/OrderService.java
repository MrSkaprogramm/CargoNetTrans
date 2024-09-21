package com.androidcargo.spring.service.impl;

import com.androidcargo.spring.models.car.BodyType;
import com.androidcargo.spring.models.order.Order;
import com.androidcargo.spring.models.order.OrderStatus;
import com.androidcargo.spring.models.user.Driver;
import com.androidcargo.spring.models.user.Mover;
import com.androidcargo.spring.models.work.DriverWork;
import com.androidcargo.spring.models.work.MoverWork;
import com.androidcargo.spring.models.work.WorkType;
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

    BigDecimal totalOrderPrice = driverPrice + moverPrice;

    return totalOrderPrice;
  }

  public BigDecimal calculateDriverPrice(Order order) {
    BigDecimal driverPrice = null;
    Driver driver = order.getDriver();

    DriverWork driverWork = driver.getCars().getFirst().getDriverWork();
    BodyType bodyType = driverWork.getCarBodyType();

    switch (bodyType){
      case TOW:
        driverPrice = driverWork.getPricePerShift(); //+ возможно driverWork.getPricePerDowntownDelivery()
      case DUMP:
        driverPrice = driverWork.getPricePerShift(); //+ возможно driverWork.getPricePerDowntownDelivery()
      case CRANE:
        driverPrice = driverWork.getPricePerShift(); //+ возможно driverWork.getPricePerDowntownDelivery()
      case AWNING:
        driverPrice = driverWork.getPricePerShift(); //+ возможно driverWork.getPricePerDowntownDelivery()
      case GRADER:
        driverPrice = driverWork.getPricePerShift(); //+ возможно driverWork.getPricePerDowntownDelivery()
      case FLATBED:
        driverPrice = driverWork.getPricePerShift(); //+ возможно driverWork.getPricePerDowntownDelivery()
      case TRACTOR:
        driverPrice = driverWork.getPricePerShift(); //+ возможно driverWork.getPricePerDowntownDelivery()
      case ALLMETAL:
        driverPrice = driverWork.getPricePerHour() + driverWork.getPricePerKm(); //нужно умножить на соответствующее количество часов и км
      case ISOTHERM:
        driverPrice = driverWork.getPricePerHour() + driverWork.getPricePerKm(); //нужно умножить на соответствующее количество часов и км
      case BULLDOZER:
        driverPrice = driverWork.getPricePerShift(); //+ возможно driverWork.getPricePerDowntownDelivery()
      case MANIPULATOR:
        driverPrice = driverWork.getPricePerShift(); //+ возможно driverWork.getPricePerDowntownDelivery()
      case REFRIGERATOR:
        driverPrice = driverWork.getPricePerHour() + driverWork.getPricePerKm(); //нужно умножить на соответствующее количество часов и км
      case CONCRETE_MIXER:
        driverPrice = driverWork.getPricePerShift(); //+ возможно driverWork.getPricePerDowntownDelivery()
      case AERIAL_PLATFORM:
        driverPrice = driverWork.getPricePerShift(); //+ возможно driverWork.getPricePerDowntownDelivery()
    }

    return driverPrice;
  }

  public BigDecimal calculateMoversPrice(Order order) {
    BigDecimal moverPrice = null;
    List<Mover> movers = order.getMovers();

    MoverWork moverWork = movers.getFirst().getMoverWorksList().getFirst();
    WorkType workType = moverWork.getMoverWorkType();

    switch (workType) {
      case DOG_WALKING:
        moverPrice = moverWork.getPricePerHour(); //+ moverWork.getPricePerDowntownWork() + нужно умножить на соответствующее количество часов
      case MOWING_GRASS:
        moverPrice = moverWork.getPricePerHour(); //+ moverWork.getPricePerDowntownWork() + нужно умножить на соответствующее количество часов
      case RIGGING_WORK:
        moverPrice = moverWork.getPricePerHour() + moverWork.getPricePerKg(); //нужно умножить на соответствующее количество часов и кг
      case SNOW_REMOVAL:
        moverPrice = moverWork.getPricePerHour(); //+ moverWork.getPricePerDowntownWork() + нужно умножить на соответствующее количество часов
      case WASTE_MOVING:
        moverPrice = moverWork.getPricePerHour() + moverWork.getPricePerKg(); //нужно умножить на соответствующее количество часов и кг
      case DIGGING_WORKS:
        moverPrice = moverWork.getPricePerHour(); //+ moverWork.getPricePerDowntownWork() + нужно умножить на соответствующее количество часов
      case OFFICE_MOVING:
        moverPrice = moverWork.getPricePerHour(); //+ moverWork.getPricePerDowntownWork() + нужно умножить на соответствующее количество часов
      case WORK_AT_HEIGHT:
        moverPrice = moverWork.getPricePerHour(); //+ moverWork.getPricePerDowntownWork() + нужно умножить на соответствующее количество часов
      case FURNITURE_MOVING:
        moverPrice = moverWork.getPricePerHour(); //+ moverWork.getPricePerDowntownWork() + нужно умножить на соответствующее количество часов
      case MOVING_SHIFT_WORK:
        moverPrice = moverWork.getPricePerShift(); //+ moverWork.getPricePerDowntownWork() + нужно умножить на соответствующее количество смен
      case LIGHT_OBJECTS_MOVING:
        moverPrice = moverWork.getPricePerHour() + moverWork.getPricePerKg(); //нужно умножить на соответствующее количество часов и кг
      case BUILDING_MATERIALS_MOVING:
        moverPrice = moverWork.getPricePerHour() + moverWork.getPricePerKg(); //нужно умножить на соответствующее количество часов и кг
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
