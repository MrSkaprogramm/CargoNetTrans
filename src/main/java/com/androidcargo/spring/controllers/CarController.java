package com.androidcargo.spring.controllers;

import com.androidcargo.spring.service.impl.DriverService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/driver/car")
public class CarController {
    private final DriverService driverService;

    @Autowired
    public CarController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/addCar")
    public String addCar(HttpSession session) {
        //передача данных в бд о новой машине
        return "/admin";
    }
}
