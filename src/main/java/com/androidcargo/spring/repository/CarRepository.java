package com.androidcargo.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.androidcargo.spring.models.car.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

}
