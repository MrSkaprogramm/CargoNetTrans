package com.androidcargo.spring.controllers;

import com.androidcargo.spring.dto.UpdateProfileRequest;
import com.androidcargo.spring.service.impl.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
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
            boolean isUpdated = clientService.updateProfile(
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

    @GetMapping("/changePassword")
    public String changePassword() {
        return "/admin";
    }
}
