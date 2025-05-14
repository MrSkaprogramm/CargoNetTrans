package com.androidcargo.spring.repository;

import com.androidcargo.spring.models.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
