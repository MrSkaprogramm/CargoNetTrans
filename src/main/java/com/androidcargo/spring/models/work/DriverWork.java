package com.androidcargo.spring.models.work;

import java.math.BigDecimal;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import com.androidcargo.spring.models.car.BodyType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DriverWork")
public class DriverWork {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "permission")
  @JdbcTypeCode(SqlTypes.NAMED_ENUM)
  private BodyType carBodyType;
  @Column(name = "permission")
  private BigDecimal pricePerHour;
  @Column(name = "permission")
  private BigDecimal pricePerKm;
  @Column(name = "permission")
  private BigDecimal pricePerShift;
  @Column(name = "permission")
  private BigDecimal pricePerDowntownDelivery;
}
