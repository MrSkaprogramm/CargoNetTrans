package com.androidcargo.spring.service;

import com.androidcargo.spring.dto.UserUpdateInfoDto;
import com.androidcargo.spring.models.admin.Admin;
import com.androidcargo.spring.models.user.Driver;

import java.util.List;
import java.util.Optional;

public interface DriverServiceInterface {

  /*public void changeCarCharacteristics();

  public void takeToWork();*/

  public List<Driver> getAllDrivers();

  public Driver findByPhone(String phone);

}
