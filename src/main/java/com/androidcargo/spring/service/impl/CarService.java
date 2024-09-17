package com.androidcargo.spring.service.impl;

import com.androidcargo.spring.models.car.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.androidcargo.spring.repository.CarRepository;
import com.androidcargo.spring.service.CarServiceInterface;

import java.util.List;
import java.util.Optional;

@Service
public class CarService implements CarServiceInterface {
  private final CarRepository carRepository;

  @Autowired
  public CarService(CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  @Override
  public void addCar(Car car) {
    carRepository.save(car);
  }

  @Override
  public void deleteCar(Car car) {
    carRepository.delete(car);
  }

  @Override
  public List<Car> getAllCars() {
    return carRepository.findAll();
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
}
