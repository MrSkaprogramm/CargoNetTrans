package com.androidcargo.spring.controllers.admin;

import com.androidcargo.spring.models.admin.Admin;
import com.androidcargo.spring.models.car.Car;
import com.androidcargo.spring.models.order.Order;
import com.androidcargo.spring.models.user.Client;
import com.androidcargo.spring.models.user.Driver;
import com.androidcargo.spring.models.user.Mover;
import com.androidcargo.spring.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
  public List<Admin> showAdmins() {
    return adminService.getAllAdmins();
  };

  @GetMapping("/showDrivers")
  public List<Driver> showDrivers() {
    return driverService.getAllDrivers();
  };

  @GetMapping("/showCars")
  public List<Car> showCars() {
    return carService.getAllCars();
  };

  @GetMapping("/showMovers")
  public List<Mover> showMovers() {
    return moverService.getAllMovers();
  };

  @GetMapping("/showClients")
  public List<Client> showClients() {
    return clientService.getAllClients();
  };

  @GetMapping("/showOrders")
  public List<Order> showOrders() {
    return orderService.getAllOrders();
  };
}
