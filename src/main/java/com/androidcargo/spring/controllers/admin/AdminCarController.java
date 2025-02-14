package com.androidcargo.spring.controllers.admin;

import com.androidcargo.spring.models.car.Car;
import com.androidcargo.spring.service.impl.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AdminCarController {
    private final CarService carService;

    @Autowired
    public AdminCarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/{id}/showCarInfo")
    public Car showCarInfo(@PathVariable int id) {
        carService.getCar(id);
        return carService.getCar(id);
    };
}
