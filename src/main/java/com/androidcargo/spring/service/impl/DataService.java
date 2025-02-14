package com.androidcargo.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.androidcargo.spring.models.data.DriverData;
import com.androidcargo.spring.repository.CarDataRepository;
import com.androidcargo.spring.repository.DriverDataRepository;
import com.androidcargo.spring.repository.MoverDataRepository;
import com.androidcargo.spring.service.DataServiceInterface;

@Service
public class DataService implements DataServiceInterface {

  private CarDataRepository carDataRepository;
  private DriverDataRepository driverDataRepository;
  private MoverDataRepository moverDataRepository;

  @Autowired
  public DataService(CarDataRepository carDataRepository, DriverDataRepository driverDataRepository, MoverDataRepository moverDataRepository) {
      this.carDataRepository = carDataRepository;
      this.driverDataRepository = driverDataRepository;
      this.moverDataRepository = moverDataRepository;
  }

  @Override
  public DriverData createDriverData(DriverData driverData) {
    return driverDataRepository.save(driverData);
  }

  @Override
  public DriverData viewDriverDataById(long id) {
    return driverDataRepository.findById(id).get();
  }

}
