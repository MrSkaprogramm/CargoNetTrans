package com.androidcargo.spring.service;

import com.androidcargo.spring.dto.UserUpdateInfoDto;
import com.androidcargo.spring.models.user.Client;

import java.util.List;
import java.util.Optional;

public interface ClientServiceInterface {

  /*public void makeOrder();

  public void cancelOrder();

  public void changeOrder();

  public void showWorkContractorCharacteristics();*/

  public List<Client> getAllClients();

}
