package com.androidcargo.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.androidcargo.spring.models.user.Mover;

@Repository
public interface MoverRepository extends JpaRepository<Mover, Integer> {
    Mover findByPhoneNumber(String phoneNumber);
}
