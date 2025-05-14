package com.androidcargo.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.androidcargo.spring.models.user.Mover;

import java.util.Optional;

@Repository
public interface MoverRepository extends JpaRepository<Mover, Integer> {
    Optional<Mover> findByPhoneNumber(String phoneNumber);

    Optional<Mover> findByEmail(String email);
    boolean existsByEmail(String email);
}
