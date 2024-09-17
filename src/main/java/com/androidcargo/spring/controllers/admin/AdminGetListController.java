package com.androidcargo.spring.controllers.admin;

import com.androidcargo.spring.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/getList")
public class AdminGetListController {
  private final AdminService adminService;
  private final ClientService clientService;
  private final DriverService driverService;
  private final MoverService moverService;
  private final CarService carService;
  private final OrderService orderService;

  @Autowired
  public AdminGetListController(AdminService adminService, ClientService clientService, DriverService driverService, MoverService moverService, CarService carService, OrderService orderService) {
        this.adminService = adminService;
        this.clientService = clientService;
        this.driverService = driverService;
        this.moverService = moverService;
        this.carService = carService;
        this.orderService = orderService;
  }

  @GetMapping("/showAdmins")
  public String showAdmins() {
    adminService.getAllAdmins();
    return "/admin";
  };

  @GetMapping("/showDrivers")
  public String showDrivers() {
    driverService.getAllDrivers();
    return "/admin";
  };

  @GetMapping("/showCars")
  public String showCars() {
    carService.getAllCars();
    return "/admin";
  };

  @GetMapping("/showMovers")
  public String showMovers() {
    moverService.getAllMovers();
    return "/admin";
  };

  @GetMapping("/showClients")
  public String showClients() {
    clientService.getAllClients();
    return "/admin";
  };

  @GetMapping("/showOrders")
  public String showOrders() {
    orderService.getAllOrders();
    return "/admin";
  };
}
