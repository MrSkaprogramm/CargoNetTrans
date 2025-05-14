package com.androidcargo.spring.service;

import com.androidcargo.spring.models.admin.Admin;
import com.androidcargo.spring.models.admin.Permission;
import com.androidcargo.spring.models.user.Mover;
import com.androidcargo.spring.models.user.User;

import java.util.List;
import java.util.Optional;

public interface AdminServiceInterface {

    void changeAdminPermission(int id, Permission permission);

    public List<Admin> getAllAdmins();

  public Optional<Admin> findByPhone(String phone);

}
