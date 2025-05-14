package com.androidcargo.spring.repository;

import com.androidcargo.spring.models.car.BodyType;
import com.androidcargo.spring.models.work.DriverWork;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DriverWorkRepository  extends JpaRepository<DriverWork, Integer> {
    Optional<DriverWork> findByCarBodyType(BodyType bodyType);

}
