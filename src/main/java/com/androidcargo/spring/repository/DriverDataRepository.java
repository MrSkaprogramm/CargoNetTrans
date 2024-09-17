package com.androidcargo.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.androidcargo.spring.models.data.DriverData;

@Repository
public interface DriverDataRepository extends JpaRepository<DriverData, Integer> {

}
