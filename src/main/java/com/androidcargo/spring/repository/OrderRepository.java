package com.androidcargo.spring.repository;

import com.androidcargo.spring.models.order.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.androidcargo.spring.models.order.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByOrderStatus(OrderStatus orderStatus);

}
