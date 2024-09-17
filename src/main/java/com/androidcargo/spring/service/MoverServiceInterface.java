package com.androidcargo.spring.service;

import com.androidcargo.spring.models.order.Order;
import com.androidcargo.spring.models.user.Mover;

import java.util.List;
import java.util.Optional;

public interface MoverServiceInterface {

  public void register(Mover mover);

  public void changePassword(int moverId, String newPassword);

  public List<Order> getMoverOrders(Integer moverId);

  public Mover blockMover(int id);

  public Mover unBlockMover(int id);

  public Mover getMover(int id);

  /*public void changeCharacteristics();

  public void changeServicesList();

  public void takeToWork();*/

  public List<Mover> getAllMovers();

  public Mover findByPhone(String phone);

  public Optional<Mover> authenticate(String phoneNumber, String password);
}
