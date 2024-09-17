package com.androidcargo.spring.service.impl;

import com.androidcargo.spring.dto.UserUpdateInfoDto;
import com.androidcargo.spring.models.admin.Admin;
import com.androidcargo.spring.models.car.Car;
import com.androidcargo.spring.models.order.Order;
import com.androidcargo.spring.models.user.Client;
import com.androidcargo.spring.models.user.Driver;
import com.androidcargo.spring.models.user.Mover;
import com.androidcargo.spring.models.user.UserStatus;
import com.androidcargo.spring.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.androidcargo.spring.repository.MoverRepository;
import com.androidcargo.spring.service.MoverServiceInterface;

import java.util.List;
import java.util.Optional;

@Service
public class MoverService implements MoverServiceInterface {
  private final MoverRepository moverRepository;
  private final OrderRepository orderRepository;

  @Autowired
  public MoverService(MoverRepository moverRepository, OrderRepository orderRepository) {
    this.moverRepository = moverRepository;
    this.orderRepository = orderRepository;
  }

  @Override
  public void register(Mover mover) {
    moverRepository.save(mover);
  }

  @Override
  public void changePassword(int moverId, String newPassword) {
    Optional<Mover> optionalMover = moverRepository.findById(moverId);

    if (optionalMover.isPresent()) {
      Mover mover = optionalMover.get();
      mover.setPassword(newPassword);
      moverRepository.save(mover);
    } else {
      throw new IllegalArgumentException("Mover not found");
    }
  }

  public void updateMoverInfo(int moverId, UserUpdateInfoDto userUpdateInfoDto) {
    Optional<Mover> optionalMover = moverRepository.findById(moverId);

    if (optionalMover.isPresent()) {
      Mover mover = optionalMover.get();
      mover.setLogin(userUpdateInfoDto.getLogin());
      mover.setName(userUpdateInfoDto.getName());
      mover.setSurname(userUpdateInfoDto.getSurname());
      mover.setPatronymic(userUpdateInfoDto.getPatronymic());
      mover.setPhoneNumber(userUpdateInfoDto.getPhoneNumber());
      mover.setEmail(userUpdateInfoDto.getEmail());

      moverRepository.save(mover);
    } else {
      throw new IllegalArgumentException("Mover not found");
    }
  }

  /*@Override
  public void changeServicesList() {
    // TODO Auto-generated method stub

  }

  @Override
  public void takeToWork() {
    // TODO Auto-generated method stub

  }

  @Override
  public void addWorkToList() {
    // TODO Auto-generated method stub

  }*/

  @Override
  public List<Order> getMoverOrders(Integer moverId) {
    Mover mover = moverRepository.findById(moverId).orElse(null);

    if (mover == null) {
      throw new IllegalArgumentException("Mover not found");
    }

    return orderRepository.findByMover(mover);
  }

  @Override
  public Mover blockMover(int id) {
    Mover mover = null;
    Optional<Mover> optionalMover = moverRepository.findById(id);
    if (optionalMover.isPresent()) {
      mover = optionalMover.get();
    } else {
      throw new IllegalArgumentException("Car not found");
    }

    mover.setUserStatus(UserStatus.BLOCKED);
    mover = moverRepository.save(mover);
    return mover;
  }

  @Override
  public Mover unBlockMover(int id) {
    Mover mover = null;
    Optional<Mover> optionalMover = moverRepository.findById(id);
    if (optionalMover.isPresent()) {
      mover = optionalMover.get();
    } else {
      throw new IllegalArgumentException("Car not found");
    }

    mover.setUserStatus(UserStatus.ACTIVE);
    mover = moverRepository.save(mover);
    return mover;
  }

  @Override
  public Mover getMover(int id) {
    Mover mover = null;
    Optional<Mover> optionalMover = moverRepository.findById(id);
    if (optionalMover.isPresent()) {
      mover = optionalMover.get();
    } else {
      throw new IllegalArgumentException("Car not found");
    }
    return mover;
  }

  @Override
  public List<Mover> getAllMovers() {
    return moverRepository.findAll();
  }

  @Override
  public Mover findByPhone(String phone) {
    return moverRepository.findByPhoneNumber(phone);
  }

  public Optional<Mover> authenticate(String phoneNumber, String password) {
    Mover mover = moverRepository.findByPhoneNumber(phoneNumber);

    if (mover != null && mover.getPassword().equals(password)) {
      return Optional.of(mover);
    }

    return Optional.empty();
  }
}
