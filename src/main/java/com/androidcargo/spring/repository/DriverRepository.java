package com.androidcargo.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.androidcargo.spring.models.user.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
}
