package com.androidcargo.spring.service.impl;

import com.androidcargo.spring.models.car.Car;
import com.androidcargo.spring.models.order.Order;
import com.androidcargo.spring.models.order.OrderStatus;
import com.androidcargo.spring.models.user.Client;
import com.androidcargo.spring.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.androidcargo.spring.repository.ClientRepository;
import com.androidcargo.spring.service.ClientServiceInterface;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements ClientServiceInterface {
  private final ClientRepository clientRepository;
  private final OrderRepository orderRepository;

  @Autowired
  public ClientService(ClientRepository clientRepository, OrderRepository orderRepository) {
    this.clientRepository = clientRepository;
    this.orderRepository = orderRepository;
  }

  public Client getClient(int id) {
    Client client = null;
    Optional<Client> optionalCar = clientRepository.findById(id);
    if (optionalCar.isPresent()) {
      client = optionalCar.get();
    } else {
      throw new IllegalArgumentException("Car not found");
    }
    return client;
  }

  public List<Order> getClientOrders(Integer clientId) {
    Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new IllegalArgumentException("Client not found"));
    return orderRepository.findByClient(client);
  }

  @Override
  public List<Client> getAllClients() {
    return clientRepository.findAll();
  }
}
