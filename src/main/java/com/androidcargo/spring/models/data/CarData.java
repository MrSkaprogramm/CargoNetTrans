package com.androidcargo.spring.models.data;

import java.sql.Blob;
import java.util.Date;

import com.androidcargo.spring.models.car.Car;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CarData")
public class CarData {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Lob
  private Blob image;
  private Date date = new Date();
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "car_id", nullable = false)
  private Car car;
}
