package com.androidcargo.spring.service.impl;

import com.androidcargo.spring.dto.UserUpdateInfoDto;
import com.androidcargo.spring.models.admin.Admin;
import com.androidcargo.spring.models.car.Car;
import com.androidcargo.spring.models.order.Order;
import com.androidcargo.spring.models.user.Client;
import com.androidcargo.spring.models.user.UserStatus;
import com.androidcargo.spring.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.androidcargo.spring.repository.ClientRepository;
import com.androidcargo.spring.service.ClientServiceInterface;

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

  @Override
  public void register(Client client) {
    clientRepository.save(client);
  }

  public void changePassword(int clientId, String newPassword) {
    Optional<Client> optionalClient = clientRepository.findById(clientId);

    if (optionalClient.isPresent()) {
      Client client = optionalClient.get();
      client.setPassword(newPassword);
      clientRepository.save(client);
    } else {
      throw new IllegalArgumentException("Client not found");
    }
  }

  @Override
  public void updateClientInfo(int clientId, UserUpdateInfoDto userUpdateInfoDto) {
    Optional<Client> optionalClient = clientRepository.findById(clientId);

    if (optionalClient.isPresent()) {
      Client client = optionalClient.get();
      client.setLogin(userUpdateInfoDto.getLogin());
      client.setName(userUpdateInfoDto.getName());
      client.setSurname(userUpdateInfoDto.getSurname());
      client.setPatronymic(userUpdateInfoDto.getPatronymic());
      client.setPhoneNumber(userUpdateInfoDto.getPhoneNumber());
      client.setEmail(userUpdateInfoDto.getEmail());

      clientRepository.save(client);
    } else {
      throw new IllegalArgumentException("Client not found");
    }
  }

  /*@Override
  public void makeOrder() {

  }

  @Override
  public void cancelOrder() {

  }

  @Override
  public void changeOrder() {

  }

  @Override
  public void showWorkContractorCharacteristics() {

  }*/

  public List<Order> getClientOrders(Integer clientId) {
    Client client = clientRepository.findById(clientId)
            .orElseThrow(() -> new IllegalArgumentException("Client not found"));
    return orderRepository.findByClient(client);
  }

  public Client blockClient(int id) {
    Client client = null;
    Optional<Client> optionalClient = clientRepository.findById(id);
    if (optionalClient.isPresent()) {
      client = optionalClient.get();
    } else {
      throw new IllegalArgumentException("Car not found");
    }

    client.setUserStatus(UserStatus.BLOCKED);
    client = clientRepository.save(client);
    return client;
  }

  public Client unBlockClient(int id) {
    Client client = null;
    Optional<Client> optionalClient = clientRepository.findById(id);
    if (optionalClient.isPresent()) {
      client = optionalClient.get();
    } else {
      throw new IllegalArgumentException("Car not found");
    }

    client.setUserStatus(UserStatus.ACTIVE);
    client = clientRepository.save(client);

    return client;
  }

  public Client getClient(int id) {
    Client client = null;
    Optional<Client> optionalClient = clientRepository.findById(id);
    if (optionalClient.isPresent()) {
      client = optionalClient.get();
    } else {
      throw new IllegalArgumentException("Car not found");
    }
    return client;
  }

  @Override
  public List<Client> getAllClients() {
    return clientRepository.findAll();
  }

  @Override
  public Client findByPhone(String phone) {
    return clientRepository.findByPhoneNumber(phone);
  }

  @Override
  public Optional<Client> authenticate(String phoneNumber, String password) {
    Client client = clientRepository.findByPhoneNumber(phoneNumber);

    if (client != null && client.getPassword().equals(password)) {
      return Optional.of(client); // Возвращаем объект водителя
    }

    return Optional.empty(); // Если аутентификация не удалась
  }
}
