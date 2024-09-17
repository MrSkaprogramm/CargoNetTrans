package com.androidcargo.spring.models.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.androidcargo.spring.models.user.Client;
import com.androidcargo.spring.models.user.Driver;
import com.androidcargo.spring.models.user.Mover;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Order")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "orderStatus")
  OrderStatus orderStatus;
  @Column(name = "distance")
  private BigDecimal distance;
  @Column(name = "totalPrice")
  private BigDecimal totalPrice;
  @Column(name = "description")
  private String description;
  @Column(name = "actualStartTime")
  private LocalDateTime actualStartTime;
  @Column(name = "actualEndTime")
  private LocalDateTime actualEndTime;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "client_id", nullable = false)
  private Client client;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "driver_id", nullable = false)
  private Driver driver;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "mover_id", nullable = false)
  private Mover mover;
}
