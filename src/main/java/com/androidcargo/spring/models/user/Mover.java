package com.androidcargo.spring.models.user;

import java.util.ArrayList;
import java.util.List;

import com.androidcargo.spring.models.order.Order;
import com.androidcargo.spring.models.work.MoverWork;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mover")
public class Mover extends User {
  /*@Column(name = "moverWorksList")
  private List<MoverWork> moverWorksList;*/
  /*@ManyToMany(mappedBy = "movers", fetch = FetchType.EAGER)
  private List<Order> orders = new ArrayList<>();*/
}
