package com.androidcargo.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.androidcargo.spring.models.admin.Admin;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByPhoneNumber(String phoneNumber);
}
