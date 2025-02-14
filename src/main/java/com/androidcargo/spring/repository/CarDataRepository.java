package com.androidcargo.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDataRepository extends JpaRepository<CarData, Integer> {

}
