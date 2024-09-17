package com.androidcargo.spring.service;

import org.springframework.stereotype.Service;
import com.androidcargo.spring.models.data.CarData;
import com.androidcargo.spring.models.data.DriverData;
import com.androidcargo.spring.models.data.MoverData;

@Service
public interface DataServiceInterface {
  
  public CarData createCarData(CarData carData);

  public CarData viewCarDataById(long id);

  public DriverData createDriverData(DriverData driverData);

  public DriverData viewDriverDataById(long id);

  public MoverData createMoverData(MoverData moverData);

  public MoverData viewByIdMoverData(long id);

}
