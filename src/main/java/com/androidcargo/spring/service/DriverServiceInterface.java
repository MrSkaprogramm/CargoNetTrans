package com.androidcargo.spring.service;

import com.androidcargo.spring.models.user.Driver;

import java.util.List;
import java.util.Optional;

public interface DriverServiceInterface {

    public List<Driver> getAllDrivers();

    public Optional<Driver> findByPhone(String phone);

    void register(Driver driver);

    boolean updateProfile(String currentEmail, String name, String newEmail);
}
