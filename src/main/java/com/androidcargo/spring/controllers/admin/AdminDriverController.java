package com.androidcargo.spring.controllers.admin;

import com.androidcargo.spring.service.impl.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/showDrivers")
public class AdminDriverController {
    private final DriverService driverService;

    @Autowired
    public AdminDriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/{id}/showInfo")
    public String showInfo(@PathVariable int id) {
        return "/admin";
    };

    @GetMapping("/{id}/showOrders")
    public String showOrders(@PathVariable int id) {
        driverService.getDriverOrders(id);
        return "/admin";
    };

    @GetMapping("/{id}/showDriver")
    public String showCar(@PathVariable int id) {
        driverService.getDriver(id);
        return "/admin";
    };

    @GetMapping("/{id}/blockDriver")
    public String blockDriver(@PathVariable int id) {
        driverService.blockDriver(id);
        return "/admin";
    };

    @GetMapping("/{id}/unBlockDriver")
    public String unBlockDriver(@PathVariable int id) {
        driverService.unBlockDriver(id);
        return "/admin";
    };
}
