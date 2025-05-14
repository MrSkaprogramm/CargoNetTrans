package com.androidcargo.spring.service.impl;

import com.androidcargo.spring.models.car.BodyType;
import com.androidcargo.spring.models.car.Car;
import com.androidcargo.spring.models.user.Driver;
import com.androidcargo.spring.models.user.User;
import com.androidcargo.spring.repository.CarRepository;
import com.androidcargo.spring.repository.DriverRepository;
import com.androidcargo.spring.repository.OrderRepository;
import com.androidcargo.spring.service.DriverServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService implements DriverServiceInterface {
  private final DriverRepository driverRepository;
  private final OrderRepository orderRepository;
  private final CarRepository carRepository;

  @Autowired
  public DriverService(DriverRepository driverRepository, OrderRepository orderRepository, CarRepository carRepository) {
    this.driverRepository = driverRepository;
    this.orderRepository = orderRepository;
      this.carRepository = carRepository;
  }

  @Override
  public List<Driver> getAllDrivers() {
    return driverRepository.findAll();
  }

  @Override
  public Optional<Driver> findByPhone(String phone) {
    return driverRepository.findByPhoneNumber(phone);
  }

  public Optional<User> authenticate(String phoneNumber, String password) {
    return driverRepository.findByPhoneNumber(phoneNumber)
            .filter(driver -> driver.getPassword().equals(password))
            .map(driver -> driver); // Приведение типа Driver к User, если нужно
  }

  @Override
  public void register(Driver driver) {
    driverRepository.save(driver);
  }

  @Override
  public boolean updateProfile(String currentPhone, String name, String newEmail) {
    // Находим водителя по текущему email
    Driver driver = driverRepository.findByPhoneNumber(currentPhone)
            .orElseThrow(() -> new EntityNotFoundException("Водитель не найден"));

    // Обновляем данные
    driver.setName(name);
    driver.setEmail(newEmail);

    // Сохраняем изменения
    driverRepository.save(driver);
    return true;
  }

  public boolean addCar(String driverPhone, BodyType bodyType, double capacity, double width, double height) {
    Car car = new Car();
    car.setBodyType(bodyType);
    car.setCapacity(capacity);
    car.setWidth(width);
    car.setHeight(height);

    // Сохраняем машину
    carRepository.save(car);
    return true;
  }
}
