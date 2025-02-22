package com.androidcargo.spring.service;

import com.androidcargo.spring.models.admin.Admin;
import com.androidcargo.spring.models.user.Mover;
import com.androidcargo.spring.models.user.User;

import java.util.List;
import java.util.Optional;

public interface AdminServiceInterface {

  public List<Admin> getAllAdmins();

  public Admin findByPhone(String phone);

}
