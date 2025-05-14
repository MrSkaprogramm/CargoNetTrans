package com.androidcargo.spring.controllers;

import com.androidcargo.spring.dto.OrderRequest;
import com.androidcargo.spring.models.order.Order;
import com.androidcargo.spring.models.order.OrderStatus;
import com.androidcargo.spring.repository.OrderRepository;
import com.androidcargo.spring.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderService orderService, OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    @GetMapping("/check-order")
    public ResponseEntity<?> checkOrder() {
        try {
            // Создаем тестовый заказ
            Order order = new Order();
            order.setAddress("Test Address");
            order.setDescription("Test Description");
            order.setFloorsQuantity(new BigDecimal("1"));
            order.setFreightQuantity(new BigDecimal("100"));
            order.setDowntownWork(false);
            order.setElevatorDelivery(true);
            order.setHeavyLoad(false);

            Order saved = orderRepository.save(order);
            System.out.println("Saved order ID: " + saved.getId());

            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/beginOrder")
    public ResponseEntity<?> beginOrder(@RequestBody OrderRequest orderRequest) {
        try {
            // Создаем новый заказ
            Order order = new Order();
            order.setAddress(orderRequest.getAddress());
            order.setDescription(orderRequest.getDescription());
            order.setFloorsQuantity(orderRequest.getFloorsQuantity());
            order.setFreightQuantity(orderRequest.getFreightQuantity());
            order.setDowntownWork(orderRequest.isDowntownWork());
            order.setElevatorDelivery(orderRequest.isElevatorDelivery());
            order.setHeavyLoad(orderRequest.isHeavyLoad());

            Order savedOrder = orderRepository.save(order);
            System.out.println("Created order ID: " + savedOrder.getId());

            return ResponseEntity.ok("Заказ создан");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка: " + e.getMessage());
        }
    }

    @GetMapping("/cancelOrder")
    public String cancelOrder() {
        //запись в бд заказа со статусом отменён
        return "/admin";
    }

    @GetMapping("/finishOrder")
    public String finishOrder() {
        //изменение в бд статуса заказа на окончен
        return "/admin";
    }

    @GetMapping("/changeOrder")
    String changeOrder() {
        //передача в бд изменённых данных заказа
        return "/admin";
    }

    @GetMapping("/searchOrders")
    public ResponseEntity<?> searchOrder(@RequestParam("status") String status) {
        try {
            // Здесь должна быть логика поиска заказов по статусу
            // Например, используя orderRepository.findByStatus(status)
            // Предположим, что у Order есть поле status

            List<Order> orders = orderRepository.findByOrderStatus(OrderStatus.valueOf(status));

            if (orders.isEmpty()) {
                return ResponseEntity.ok().body(Collections.emptyList());
            }

            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка при поиске заказов: " + e.getMessage());
        }
    }
}