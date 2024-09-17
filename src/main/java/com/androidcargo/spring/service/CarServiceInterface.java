package com.androidcargo.spring.service;

import com.androidcargo.spring.models.car.Car;

import java.util.List;

public interface CarServiceInterface {

  void addCar(Car car);

  void deleteCar(Car car);

  public List<Car> getAllCars();
}
