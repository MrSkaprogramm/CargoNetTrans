package com.androidcargo.spring.models.work;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MoverWork")
public class MoverWork {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "moverWorkType")
  private WorkType moverWorkType;
  @Column(name = "pricePerWork")
  private BigDecimal pricePerWork;
  @Column(name = "pricePerHour")
  private BigDecimal pricePerHour;
  @Column(name = "pricePerKg")
  private BigDecimal pricePerKg;
  @Column(name = "pricePerTeamWork")
  private BigDecimal pricePerTeamWork;
  @Column(name = "pricePerShift")
  private BigDecimal pricePerShift;
  @Column(name = "pricePerDowntownWork")
  private BigDecimal pricePerDowntownWork;
}
