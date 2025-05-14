package com.androidcargo.spring.models.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.androidcargo.spring.models.user.Client;
import com.androidcargo.spring.models.user.Driver;
import com.androidcargo.spring.models.user.Mover;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Entity
@Table(name = "\"order\"")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @JdbcTypeCode(SqlTypes.NAMED_ENUM)
  @Column(name = "order_status")
  OrderStatus orderStatus;
  @Column(name = "address")
  private String address;
  @Column(name = "total_price")
  private BigDecimal totalPrice;
  @Column(name = "description")
  private String description;
  @Column(name = "actual_start_time")
  private LocalDateTime actualStartTime;
  @Column(name = "actual_end_time")
  private LocalDateTime actualEndTime;
  @Column(name = "is_downtown_work")
  private boolean isDowntownWork;
  @Column(name = "is_elevator_delivery")
  private boolean isElevatorDelivery;
  @Column(name = "distance")
  private BigDecimal distance;
  @Column(name = "is_heavy_load")
  private boolean isHeavyLoad;
  @Column(name = "freight_quantity")
  private BigDecimal freightQuantity;
  @Column(name = "floors_quantity")
  private BigDecimal floorsQuantity;
  /*@ManyToOne(fetch = FetchType.EAGER)
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
  private List<Mover> movers = new ArrayList<>();*/
}
