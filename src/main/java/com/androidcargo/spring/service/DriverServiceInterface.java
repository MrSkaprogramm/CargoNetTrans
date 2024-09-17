package com.androidcargo.spring.service;

import com.androidcargo.spring.dto.UserUpdateInfoDto;
import com.androidcargo.spring.models.admin.Admin;
import com.androidcargo.spring.models.user.Driver;

import java.util.List;
import java.util.Optional;

public interface DriverServiceInterface {

  public void register(Driver driver);

  public void updateDriverInfo(int driverId, UserUpdateInfoDto userUpdateInfoDto);

  /*public void changeCarCharacteristics();

  public void takeToWork();*/

  public List<Driver> getAllDrivers();

  public Driver findByPhone(String phone);

  public Optional<Driver> authenticate(String phoneNumber, String password);

}
