package com.androidcargo.spring.service.impl;

import com.androidcargo.spring.models.user.Client;
import com.androidcargo.spring.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
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
  public Client getClient(int id) {
    return clientRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Client not found"));
  }

  @Override
  public List<Client> getAllClients() {
    return clientRepository.findAll();
  }

  @Override
  public Optional<Client> findByPhone(String phone) {
    return clientRepository.findByPhoneNumber(phone);
  }

  @Override
  public void register(Client client) {
    clientRepository.save(client);
  }

  @Override
  public Optional<Client> authenticate(String phoneNumber, String password) {
    return clientRepository.findByPhoneNumber(phoneNumber)
            .filter(client -> client.getPassword().equals(password));
  }

  @Override
  public boolean updateProfile(String currentPhone, String name, String newEmail) {
    // Находим клиента по текущему телефону
    Client client = clientRepository.findByPhoneNumber(currentPhone)
            .orElseThrow(() -> new EntityNotFoundException("Клиент не найден"));

    // Проверяем, не занят ли новый email другим пользователем
    if (!client.getEmail().equals(newEmail)) {
      if (clientRepository.existsByEmail(newEmail)) {
        throw new IllegalArgumentException("Email уже используется другим пользователем");
      }
    }

    // Обновляем данные
    client.setName(name);
    client.setEmail(newEmail);

    // Сохраняем изменения
    clientRepository.save(client);
    return true;
  }
}
