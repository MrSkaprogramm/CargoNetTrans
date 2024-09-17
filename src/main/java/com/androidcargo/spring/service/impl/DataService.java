package com.androidcargo.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.androidcargo.spring.models.data.CarData;
import com.androidcargo.spring.models.data.DriverData;
import com.androidcargo.spring.models.data.MoverData;
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
  public CarData createCarData(CarData carData) {
    return carDataRepository.save(carData);
  }

  @Override
  public CarData viewCarDataById(long id) {
    return carDataRepository.getById(id);
  }

  @Override
  public DriverData createDriverData(DriverData driverData) {
    return driverDataRepository.save(driverData);
  }

  @Override
  public DriverData viewDriverDataById(long id) {
    return driverDataRepository.findById(id).get();
  }

  @Override
  public MoverData createMoverData(MoverData moverData) {
    return moverDataRepository.save(moverData);
  }

  @Override
  public MoverData viewByIdMoverData(long id) {
    return moverDataRepository.findById(id).get();
  }

}
