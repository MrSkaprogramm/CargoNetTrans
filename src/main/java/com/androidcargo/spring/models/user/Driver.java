package com.androidcargo.spring.models.user;

import com.androidcargo.spring.models.car.Car;
import com.androidcargo.spring.models.data.DriverData;
import com.androidcargo.spring.models.order.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Driver")
public class Driver {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int driverId;
  @Column(name = "mowingWork")
  private boolean mowingWork;
  @OneToMany(mappedBy = "cars", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Car> cars = new ArrayList<>();
  @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<Order> orders = new ArrayList<>();
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<DriverData> driverDataList = new ArrayList<>();
}
