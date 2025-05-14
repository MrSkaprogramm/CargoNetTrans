package com.androidcargo.spring.service;

import com.androidcargo.spring.models.order.Order;
import com.androidcargo.spring.models.user.Mover;

import java.util.List;
import java.util.Optional;

public interface MoverServiceInterface {

    public Mover getMover(int id);

    public List<Mover> getAllMovers();

    public Optional<Mover> findByPhone(String phone);

    void register(Mover mover);

    Optional<Mover> authenticate(String phoneNumber, String password);

    boolean updateProfile(String currentEmail, String name, String newEmail);
}
