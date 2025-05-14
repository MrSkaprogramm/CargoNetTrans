package com.androidcargo.spring.models.user;

import com.androidcargo.spring.models.order.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "driver")
public class Driver extends User {
  @Column(name = "mowingWork")
  private boolean mowingWork;
  /*@OneToMany(mappedBy = "cars", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Car> cars = new ArrayList<>();*/
  /*@OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<Order> orders = new ArrayList<>();*/
}
