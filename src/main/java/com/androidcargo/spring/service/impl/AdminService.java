package com.androidcargo.spring.service.impl;

import com.androidcargo.spring.models.admin.Admin;
import com.androidcargo.spring.models.car.Car;
import com.androidcargo.spring.models.user.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.androidcargo.spring.repository.AdminRepository;
import com.androidcargo.spring.service.AdminServiceInterface;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements AdminServiceInterface {
  private final AdminRepository adminRepository;

  @Autowired
  public AdminService(AdminRepository adminRepository, CarService carService, ClientService clientService, DataService dataService, DriverService driverService, MoverService moverService, OrderService orderService) {
    this.adminRepository = adminRepository;
  }

  @Override
  public void register(Admin admin) {
    adminRepository.save(admin);
  }

  /*@Override
  public void blockUser() {
    // TODO Auto-generated method stub

  }

  @Override
  public void unBlockUser() {

  }

  @Override
  public void changeAdminPermission() {

  }*/

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
  public Admin findByPhone(String phone) {
    return adminRepository.findByPhoneNumber(phone);
  }

  public Optional<Admin> authenticate(String phoneNumber, String password) {
    Admin admin = adminRepository.findByPhoneNumber(phoneNumber);

    if (admin != null && admin.getPassword().equals(password)) {
      return Optional.of(admin);
    }

    return Optional.empty();
  }
}
