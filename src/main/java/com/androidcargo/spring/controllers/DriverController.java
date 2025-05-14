package com.androidcargo.spring.controllers;

import com.androidcargo.spring.dto.AddCarRequest;
import com.androidcargo.spring.dto.UpdateProfileRequest;
import com.androidcargo.spring.models.car.BodyType;
import com.androidcargo.spring.models.order.Order;
import com.androidcargo.spring.service.impl.DriverService;
import com.androidcargo.spring.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverController {
    private final DriverService driverService;
    private final OrderService orderService;

    @Autowired
    public DriverController(DriverService driverService, OrderService orderService) {
        this.driverService = driverService;
        this.orderService = orderService;
    }

    @GetMapping("/available-orders")
    public ResponseEntity<List<Order>> getAvailableOrders() {
        try {
            List<Order> orders = orderService.getAvailableOrders();
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping("/accept-order/{orderId}")
    public ResponseEntity<Void> acceptOrder(
            @PathVariable Integer orderId,
            @RequestParam String driverPhone) {
        try {
            boolean success = orderService.acceptOrder(orderId, driverPhone);
            if (success) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/update-profile")
    public ResponseEntity<?> changeCharacteristics(@RequestBody UpdateProfileRequest request) {
        try {
            // Валидация входных данных
            if (request.getCurrentPhone() == null || request.getCurrentPhone().isEmpty()) {
                return ResponseEntity.badRequest().body("Текущий телефон обязателен");
            }

            if (request.getName() == null || request.getName().isEmpty()) {
                return ResponseEntity.badRequest().body("Имя обязательно");
            }

            if (request.getNewEmail() == null || request.getNewEmail().isEmpty()) {
                return ResponseEntity.badRequest().body("Новый email обязателен");
            }

            // Обновление профиля
            boolean isUpdated = driverService.updateProfile(
                    request.getCurrentPhone(),
                    request.getName(),
                    request.getNewEmail()
            );

            if (isUpdated) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Ошибка сервера: " + e.getMessage());
        }
    }

    @PostMapping("/car/addCar")
    public ResponseEntity<?> addDriverCar(@RequestBody AddCarRequest request) {
        try {

            // Вызов сервиса для добавления автомобиля
            boolean isAdded = driverService.addCar(
                    request.getDriverPhone(),
                    BodyType.valueOf(request.getBodyType()),
                    request.getCapacity(),
                    request.getWidth(),
                    request.getHeight()
            );

            if (isAdded) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().body("Не удалось добавить автомобиль");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Ошибка сервера: " + e.getMessage());
        }
    }
}
