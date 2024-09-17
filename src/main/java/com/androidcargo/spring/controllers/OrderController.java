package com.androidcargo.spring.controllers;

import com.androidcargo.spring.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/beginOrder")
    String beginOrder() {
        return "/admin";
    }

    @GetMapping("/makeOrder")
    String makeOrder() {
        return "/admin";
    }

    @GetMapping("/changeOrder")
    String changeOrder() {
        return "/admin";
    }
}