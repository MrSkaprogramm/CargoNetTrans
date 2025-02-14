package com.androidcargo.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.androidcargo.spring.models.user.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
}
