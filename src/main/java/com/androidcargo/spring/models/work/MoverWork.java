package com.androidcargo.spring.models.work;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mover_work")
public class MoverWork {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "moverWorkType")
  private WorkType moverWorkType;
  @Column(name = "freight")
  private BigDecimal freight;
  @Column(name = "pricePerFreightHour")
  private BigDecimal pricePerFreightHour;
  @Column(name = "pricePerDeliveryHour")
  private BigDecimal pricePerDeliveryHour;
  @Column(name = "pricePerFloorLifting")
  private BigDecimal pricePerFloorLifting;
  @Column(name = "pricePerHeavyLoadFreightHour")
  private BigDecimal pricePerHeavyLoadFreightHour;
}
