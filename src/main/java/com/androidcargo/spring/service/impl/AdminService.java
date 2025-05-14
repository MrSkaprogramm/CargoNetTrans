package com.androidcargo.spring.service.impl;

import com.androidcargo.spring.models.admin.Admin;
import com.androidcargo.spring.models.admin.Permission;
import com.androidcargo.spring.models.car.BodyType;
import com.androidcargo.spring.models.user.Driver;
import com.androidcargo.spring.models.work.DriverWork;
import com.androidcargo.spring.models.work.MoverWork;
import com.androidcargo.spring.models.work.WorkType;
import com.androidcargo.spring.repository.DriverWorkRepository;
import com.androidcargo.spring.repository.MoverWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.androidcargo.spring.repository.AdminRepository;
import com.androidcargo.spring.service.AdminServiceInterface;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements AdminServiceInterface {
  private final AdminRepository adminRepository;
  private final DriverWorkRepository driverWorkRepository;
  private final MoverWorkRepository moverWorkRepository;

  @Autowired
  public AdminService(AdminRepository adminRepository, DriverWorkRepository driverWorkRepository, MoverWorkRepository moverWorkRepository) {
    this.adminRepository = adminRepository;
      this.driverWorkRepository = driverWorkRepository;
      this.moverWorkRepository = moverWorkRepository;
  }

  @Override
  public void changeAdminPermission(int id, Permission permission) {
    Admin admin = getAdmin(id);
    admin.setPermission(permission);
    adminRepository.save(admin);
  }

  public Admin getAdmin(int id) {
    Admin admin = null;
    Optional<Admin> optionalAdmin = adminRepository.findById(id);
    if (optionalAdmin.isPresent()) {
      admin = optionalAdmin.get();
    } else {
      throw new IllegalArgumentException("Car not found");
    }
    return admin;
  }

  @Override
  public List<Admin> getAllAdmins() {
    return adminRepository.findAll();
  }

  @Override
  public Optional<Admin> findByPhone(String phone) {
    return adminRepository.findByPhoneNumber(phone);
  }

  public DriverWork saveDriverRate(DriverWork driverWork) {
    if (driverWork == null) {
      throw new IllegalArgumentException("DriverWork не может быть null");
    }

    // Проверяем существование тарифа для данного типа кузова
    Optional<DriverWork> existingRate = driverWorkRepository.findByCarBodyType(driverWork.getCarBodyType());

    if (existingRate.isPresent()) {
      // Обновляем существующий тариф
      DriverWork existing = existingRate.get();
      existing.setFreight(driverWork.getFreight());
      existing.setPricePerFreightHour(driverWork.getPricePerFreightHour());
      existing.setPricePerDowntownKm(driverWork.getPricePerDowntownKm());
      return driverWorkRepository.save(existing);
    } else {
      // Создаем новый тариф
      return driverWorkRepository.save(driverWork);
    }
  }

  /**
   * Получение тарифа по типу кузова
   * @param bodyType тип кузова
   * @return Optional с найденным тарифом или пустой, если не найден
   */
  public Optional<DriverWork> getDriverRateByBodyType(BodyType bodyType) {
    if (bodyType == null) {
      throw new IllegalArgumentException("Тип кузова не может быть null");
    }
    return driverWorkRepository.findByCarBodyType(bodyType);
  }

  /**
   * Получение всех тарифов водителей
   * @return список всех тарифов
   */
  public List<DriverWork> getAllDriverRates() {
    return driverWorkRepository.findAll();
  }

  public List<MoverWork> getAllLoaderRates() {
    return moverWorkRepository.findAll();
  }

  public Optional<MoverWork> getLoaderRateByWorkType(WorkType workType) {
    if (workType == null) {
      throw new IllegalArgumentException("Тип работы не может быть null");
    }
    return moverWorkRepository.findByMoverWorkType(workType);
  }

  public MoverWork saveLoaderRate(MoverWork moverWork) {
    if (moverWork == null) {
      throw new IllegalArgumentException("MoverWork не может быть null");
    }

    Optional<MoverWork> existingRate = moverWorkRepository.findByMoverWorkType(moverWork.getMoverWorkType());

    if (existingRate.isPresent()) {
      MoverWork existing = existingRate.get();
      // Обновляем все поля
      existing.setFreight(moverWork.getFreight());
      existing.setPricePerFreightHour(moverWork.getPricePerFreightHour());
      existing.setPricePerDeliveryHour(moverWork.getPricePerDeliveryHour());
      existing.setPricePerFloorLifting(moverWork.getPricePerFloorLifting());
      existing.setPricePerHeavyLoadFreightHour(moverWork.getPricePerHeavyLoadFreightHour());
      return moverWorkRepository.save(existing);
    }
    return moverWorkRepository.save(moverWork);
  }
}
