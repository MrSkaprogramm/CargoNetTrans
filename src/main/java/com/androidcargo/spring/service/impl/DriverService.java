package com.androidcargo.spring.service.impl;

import com.androidcargo.spring.dto.UserUpdateInfoDto;
import com.androidcargo.spring.models.order.Order;
import com.androidcargo.spring.models.user.Driver;
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

  @Override
  public void register(Driver driver) {
    driverRepository.save(driver);
  }

  public void changePassword(int driverId, String newPassword) {
    Optional<Driver> optionalDriver = driverRepository.findById(driverId);

    if (optionalDriver.isPresent()) {
      Driver driver = optionalDriver.get();
      driver.setPassword(newPassword);
      driverRepository.save(driver);
    } else {
      throw new IllegalArgumentException("Driver not found");
    }
  }

  @Override
  public void updateDriverInfo(int driverId, UserUpdateInfoDto userUpdateInfoDto) {
    Optional<Driver> optionalDriver = driverRepository.findById(driverId);

    if (optionalDriver.isPresent()) {
        Driver driver = optionalDriver.get();
        driver.setLogin(userUpdateInfoDto.getLogin());
        driver.setName(userUpdateInfoDto.getName());
        driver.setSurname(userUpdateInfoDto.getSurname());
        driver.setPatronymic(userUpdateInfoDto.getPatronymic());
        driver.setPhoneNumber(userUpdateInfoDto.getPhoneNumber());
        driver.setEmail(userUpdateInfoDto.getEmail());

        driverRepository.save(driver);
    } else {
        throw new IllegalArgumentException("Driver not found");
    }

  }
/*
  @Override
  public void changeCarCharacteristics() {
    // TODO Auto-generated method stub

  }

  @Override
  public void takeToWork() {
    // TODO Auto-generated method stub

  }*/

  public List<Order> getDriverOrders(Integer driverId) {
    Driver driver = driverRepository.findById(driverId).orElse(null);

    if (driver == null) {
      throw new IllegalArgumentException("Driver not found");
    }

    List<Order> orders = orderRepository.findByDriver(driver);
    return orders;
  }

  public Driver blockDriver(int id) {
    Driver driver = null;
    Optional<Driver> optionalDriver = driverRepository.findById(id);
    if (optionalDriver.isPresent()) {
      driver = optionalDriver.get();
    } else {
      throw new IllegalArgumentException("Car not found");
    }

    driver.setUserStatus(UserStatus.BLOCKED);
    driver = driverRepository.save(driver);
    return driver;
  }

  public Driver unBlockDriver(int id) {
    Driver driver = null;
    Optional<Driver> optionalDriver = driverRepository.findById(id);
    if (optionalDriver.isPresent()) {
      driver = optionalDriver.get();
    } else {
      throw new IllegalArgumentException("Car not found");
    }

    driver.setUserStatus(UserStatus.ACTIVE);
    driver = driverRepository.save(driver);

    return driver;
  }

  public Driver getDriver(int id) {
    Driver driver = null;
    Optional<Driver> optionalDriver = driverRepository.findById(id);
    if (optionalDriver.isPresent()) {
      driver = optionalDriver.get();
    } else {
      throw new IllegalArgumentException("Car not found");
    }
    return driver;
  }

  @Override
  public List<Driver> getAllDrivers() {
    return driverRepository.findAll();
  }

  @Override
  public Driver findByPhone(String phone) {
    return driverRepository.findByPhoneNumber(phone);
  }

  public Optional<Driver> authenticate(String phoneNumber, String password) {
    Driver driver = driverRepository.findByPhoneNumber(phoneNumber);

    if (driver != null && driver.getPassword().equals(password)) {
      return Optional.of(driver); // Возвращаем объект водителя
    }

    return Optional.empty(); // Если аутентификация не удалась
  }
}
