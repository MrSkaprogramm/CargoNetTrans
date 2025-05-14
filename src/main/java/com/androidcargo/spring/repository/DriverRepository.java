package com.androidcargo.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.androidcargo.spring.models.user.Driver;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
    Optional<Driver> findByPhoneNumber(String phoneNumber);

    Optional<Driver> findByEmail(String email);
    boolean existsByEmail(String email);
}
