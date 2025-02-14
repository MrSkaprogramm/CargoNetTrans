package com.androidcargo.spring.service;

import org.springframework.stereotype.Service;
import com.androidcargo.spring.models.data.DriverData;

@Service
public interface DataServiceInterface {

  public DriverData createDriverData(DriverData driverData);

  public DriverData viewDriverDataById(long id);

}
