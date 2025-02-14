package com.androidcargo.spring.service.impl;

import com.androidcargo.spring.dto.UserUpdateInfoDto;
import com.androidcargo.spring.models.order.Order;
import com.androidcargo.spring.models.user.Driver;
import com.androidcargo.spring.models.user.User;
import com.androidcargo.spring.models.user.UserStatus;
import com.androidcargo.spring.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.androidcargo.spring.repository.DriverRepository;
import com.androidcargo.spring.service.DriverServiceInterface;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService implements DriverServiceInterface {
  private final DriverRepository driverRepository;
  private final OrderRepository orderRepository;

  @Autowired
  public DriverService(DriverRepository driverRepository, OrderRepository orderRepository) {
    this.driverRepository = driverRepository;
      this.orderRepository = orderRepository;
  }

/*
  @Override
  public void changeCarCharacteristics() {
    // TODO Auto-generated method stub

  }*/

  @Override
  public void takeToWork(Order order, Driver driver) {
    order.setDriver(order);

    orderRepository.save(order);
  }

  public List<Order> getDriverOrders(Integer driverId) {
    Driver driver = driverRepository.findById(driverId).orElse(null);

    if (driver == null) {
      throw new IllegalArgumentException("Driver not found");
    }

    List<Order> orders = orderRepository.findByDriver(driver);
    return orders;
  }

  @Override
  public List<Driver> getAllDrivers() {
    return driverRepository.findAll();
  }

  @Override
  public Driver findByPhone(String phone) {
    return driverRepository.findByPhoneNumber(phone);
  }

  public Optional<User> authenticate(String phoneNumber, String password) {
    Driver driver = driverRepository.findByPhoneNumber(phoneNumber);

    if (driver != null && driver.getPassword().equals(password)) {
      return Optional.of(driver); // Возвращаем объект водителя
    }

    return Optional.empty(); // Если аутентификация не удалась
  }
}
