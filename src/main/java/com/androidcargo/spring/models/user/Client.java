package com.androidcargo.spring.models.user;

import com.androidcargo.spring.models.order.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();
}
