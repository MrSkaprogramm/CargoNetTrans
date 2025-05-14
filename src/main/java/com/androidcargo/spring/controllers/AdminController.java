package com.androidcargo.spring.controllers;

import com.androidcargo.spring.models.admin.Admin;
import com.androidcargo.spring.models.car.BodyType;
import com.androidcargo.spring.models.order.Order;
import com.androidcargo.spring.models.user.Client;
import com.androidcargo.spring.models.user.Driver;
import com.androidcargo.spring.models.user.Mover;
import com.androidcargo.spring.models.work.DriverWork;
import com.androidcargo.spring.models.work.MoverWork;
import com.androidcargo.spring.models.work.WorkType;
import com.androidcargo.spring.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final ClientService clientService;
    private final DriverService driverService;
    private final OrderService orderService;
    private final MoverService moverService;

    @Autowired
    public AdminController(AdminService adminService, ClientService clientService, DriverService driverService, OrderService orderService, MoverService moverService) {
        this.adminService = adminService;
        this.clientService = clientService;
        this.driverService = driverService;
        this.orderService = orderService;
        this.moverService = moverService;
    }

    @GetMapping("/showClients")
    public ResponseEntity<List<Client>> showClients() {
        List<Client> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/showDrivers")
    public ResponseEntity<List<Driver>> getAllDrivers() {
        List<Driver> drivers = driverService.getAllDrivers();
        return ResponseEntity.ok(drivers);
    };

    @GetMapping("/showMovers")
    public ResponseEntity<List<Mover>> getAllMovers() {
        List<Mover> movers = moverService.getAllMovers();
        return ResponseEntity.ok(movers);
    };

    @GetMapping("/showAdmins")
    public ResponseEntity<List<Admin>> showAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    };

    @GetMapping("/showOrders")
    public ResponseEntity<List<Order>> showOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    };

    @GetMapping("/driver-rates")
    public ResponseEntity<List<DriverWork>> getAllDriverRates() {
        try {
            List<DriverWork> rates = adminService.getAllDriverRates();
            if (rates.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(rates);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("driver-rates/{bodyType}")
    public ResponseEntity<DriverWork> getDriverRateByBodyType(@PathVariable String bodyType) {
        try {
            Optional<DriverWork> rate = adminService.getDriverRateByBodyType(BodyType.valueOf(bodyType));
            return rate.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/update-driver-rates")
    public ResponseEntity<Void> updateDriverRate(@RequestBody DriverWork driverWork) {
        try {
            adminService.saveDriverRate(driverWork);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/loader-rates")
    public ResponseEntity<List<MoverWork>> getAllLoaderRates() {
        try {
            List<MoverWork> rates = adminService.getAllLoaderRates();
            if (rates.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(rates);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/loader-rates/{workType}")
    public ResponseEntity<MoverWork> getLoaderRateByWorkType(@PathVariable String workType) {
        try {
            Optional<MoverWork> rate = adminService.getLoaderRateByWorkType(WorkType.valueOf(workType));
            return rate.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/update-loader-rates")
    public ResponseEntity<Void> updateLoaderRate(@RequestBody MoverWork moverWork) {
        try {
            adminService.saveLoaderRate(moverWork);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
