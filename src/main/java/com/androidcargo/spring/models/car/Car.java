package com.androidcargo.spring.models.car;

import com.androidcargo.spring.models.data.CarData;
import com.androidcargo.spring.models.work.DriverWork;
import com.androidcargo.spring.models.work.MoverWork;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Car")
public class Car {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "driverWorksList")
  private List<DriverWork> driverWorksList;
  private BodyType bodyType;
  @Column(name = "capacity")
  private double capacity;
  @Column(name = "width")
  private double width;
  @Column(name = "height")
  private double height;
  @Column(name = "driverId")
  private int driverId;
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<CarData> carDataList = new ArrayList<>();
}
