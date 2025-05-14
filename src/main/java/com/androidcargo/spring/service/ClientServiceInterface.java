package com.androidcargo.spring.service;

import com.androidcargo.spring.models.order.Order;
import com.androidcargo.spring.models.user.Client;

import java.util.List;
import java.util.Optional;

public interface ClientServiceInterface {

    Client getClient(int id);

    public List<Client> getAllClients();

    Optional<Client> findByPhone(String phone);

    void register(Client client);

    Optional<Client> authenticate(String phoneNumber, String password);

    boolean updateProfile(String currentPhone, String name, String newEmail);
}
