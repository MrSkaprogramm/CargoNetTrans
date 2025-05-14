package com.androidcargo.spring.service.impl;

import com.androidcargo.spring.models.user.Mover;
import com.androidcargo.spring.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
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
  public Mover getMover(int id) {
    return moverRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Mover not found"));
  }

  @Override
  public List<Mover> getAllMovers() {
    return moverRepository.findAll();
  }

  @Override
  public Optional<Mover> findByPhone(String phone) {
    return moverRepository.findByPhoneNumber(phone);
  }

  @Override
  public void register(Mover mover) {
    moverRepository.save(mover);
  }

  @Override
  public Optional<Mover> authenticate(String phoneNumber, String password) {
    return moverRepository.findByPhoneNumber(phoneNumber)
            .filter(mover -> mover.getPassword().equals(password));
  }

  @Override
  public boolean updateProfile(String currentPhone, String name, String newEmail) {
    // Находим грузчика по текущему email
    Mover mover = moverRepository.findByPhoneNumber(currentPhone)
            .orElseThrow(() -> new EntityNotFoundException("Грузчик не найден"));

    // Обновляем данные
    mover.setName(name);
    mover.setEmail(newEmail);

    // Сохраняем изменения
    moverRepository.save(mover);
    return true;
  }
}
