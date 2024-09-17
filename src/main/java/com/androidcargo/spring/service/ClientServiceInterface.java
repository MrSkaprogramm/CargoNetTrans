package com.androidcargo.spring.service;

import com.androidcargo.spring.dto.UserUpdateInfoDto;
import com.androidcargo.spring.models.user.Client;

import java.util.List;
import java.util.Optional;

public interface ClientServiceInterface {

  public void register(Client client);

  public void updateClientInfo(int clientId, UserUpdateInfoDto userUpdateInfoDto);

  /*public void makeOrder();

  public void cancelOrder();

  public void changeOrder();

  public void showWorkContractorCharacteristics();*/

  public List<Client> getAllClients();

  public Client findByPhone(String phone);

  public Optional<Client> authenticate(String phoneNumber, String password);
}
