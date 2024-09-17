package com.androidcargo.spring.controllers.driver;

import com.androidcargo.spring.service.impl.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/driver/car")
public class DriverCarController {
    private final DriverService driverService;

    @Autowired
    public DriverCarController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/addCar")
    public String addCar(HttpSession session) {
        return "/admin";
    }

    @GetMapping("/changeCar")
    public String changeCar(HttpSession session) {
        return "/admin";
    }

    @GetMapping("/showCar")
    public String showCar(HttpSession session) {
        return "/admin";
    }
}
