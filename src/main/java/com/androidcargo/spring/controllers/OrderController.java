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
        //запись в бд нового заказа со статусом в процессе
        return "/admin";
    }

    @GetMapping("/cancelOrder")
    public String cancelOrder() {
        //запись в бд заказа со статусом отменён
        return "/admin";
    }

    @GetMapping("/makeOrder")
    String finishOrder() {
        //изменение в бд статуса заказа на окончен
        return "/admin";
    }

    @GetMapping("/changeOrder")
    String changeOrder() {
        //передача в бд изменённых данных заказа
        return "/admin";
    }
}