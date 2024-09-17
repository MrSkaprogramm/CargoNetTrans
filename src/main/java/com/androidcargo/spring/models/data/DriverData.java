package com.androidcargo.spring.models.data;

import java.sql.Blob;
import java.util.Date;

import com.androidcargo.spring.models.user.Driver;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "DriverData")
public class DriverData {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Lob
  private Blob image;
  private Date date = new Date();
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "driver_id", nullable = false)
  private Driver driver;
}
