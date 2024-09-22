package com.androidcargo.spring.models.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.androidcargo.spring.models.Location;
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
  @Column(name = "totalPrice")
  private BigDecimal totalPrice;
  @Column(name = "description")
  private String description;
  @Column(name = "actualStartTime")
  private LocalDateTime actualStartTime;
  @Column(name = "actualEndTime")
  private LocalDateTime actualEndTime;
  @Column(name = "isDowntownWork")
  private boolean isDowntownWork;
  @Column(name = "isElevatorDelivery;")
  private boolean isElevatorDelivery;
  @Column(name = "isHeavyLoad")
  private boolean isHeavyLoad;
  @Column(name = "freightQuantity")
  private BigDecimal freightQuantity;
  @Column(name = "floorsQuantity")
  private BigDecimal floorsQuantity;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "client_id", nullable = false)
  private Client client;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "driver_id", nullable = false)
  private Driver driver;
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
          name = "order_has_mover",
          joinColumns = @JoinColumn(name = "order_id"),
          inverseJoinColumns = @JoinColumn(name = "mover_id")
  )
  private List<Mover> movers = new ArrayList<>();
  @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "location_id", unique = true, nullable = false)
  private Location location;
}
