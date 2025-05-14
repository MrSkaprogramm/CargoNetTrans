package com.androidcargo.spring.models.work;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import com.androidcargo.spring.models.car.BodyType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "driver_work")
public class DriverWork {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "car_body_type")
  @JdbcTypeCode(SqlTypes.NAMED_ENUM)
  private BodyType carBodyType;
  @Column(name = "freight")
  private BigDecimal freight;
  @Column(name = "price_per_downtown_km")
  private BigDecimal pricePerFreightHour;
  @Column(name = "price_per_freight_hour")
  private BigDecimal pricePerDowntownKm;
}
