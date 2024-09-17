package com.androidcargo.spring.controllers;

import javax.servlet.http.HttpSession;

import com.androidcargo.spring.dto.UserLoginDto;
import com.androidcargo.spring.models.admin.Admin;
import com.androidcargo.spring.models.user.Client;
import com.androidcargo.spring.models.user.Driver;
import com.androidcargo.spring.models.user.Mover;
import com.androidcargo.spring.models.user.User;
import com.androidcargo.spring.service.impl.AdminService;
import com.androidcargo.spring.service.impl.ClientService;
import com.androidcargo.spring.service.impl.DriverService;
import com.androidcargo.spring.service.impl.MoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class AuthController {
  private final AdminService adminService;
  private final ClientService clientService;
  private final DriverService driverService;
  private final MoverService moverService;

  @Autowired
  public AuthController(AdminService adminService, ClientService clientService, DriverService driverService, MoverService moverService) {
    this.adminService = adminService;
    this.clientService = clientService;
    this.driverService = driverService;
    this.moverService = moverService;
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody UserLoginDto userLoginDto, HttpSession session) {
    switch (userLoginDto.getRole()) {
      case "ROLE_ADMIN":
        return authenticateAdmin(userLoginDto, session);
      case "ROLE_CLIENT":
        return authenticateClient(userLoginDto, session);
      case "ROLE_DRIVER":
        return authenticateDriver(userLoginDto, session);
      case "ROLE_MOVER":
        return authenticateMover(userLoginDto, session);
      default:
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid role");
    }
  }

  private ResponseEntity<String> authenticateAdmin(UserLoginDto userLoginDto, HttpSession session) {
    Optional<Admin> adminOpt = adminService.authenticate(userLoginDto.getPhoneNumber(), userLoginDto.getPassword());
    if (adminOpt.isPresent()) {
      session.setAttribute("user", adminOpt.get());
      return ResponseEntity.ok("Login successful as Admin");
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
  }

  private ResponseEntity<String> authenticateClient(UserLoginDto userLoginDto, HttpSession session) {
    Optional<Client> clientOpt = clientService.authenticate(userLoginDto.getPhoneNumber(), userLoginDto.getPassword());
    if (clientOpt.isPresent()) {
      session.setAttribute("user", clientOpt.get());
      return ResponseEntity.ok("Login successful as Client");
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
  }

  private ResponseEntity<String> authenticateDriver(UserLoginDto userLoginDto, HttpSession session) {
    Optional<Driver> driverOpt = driverService.authenticate(userLoginDto.getPhoneNumber(), userLoginDto.getPassword());
    if (driverOpt.isPresent()) {
      session.setAttribute("user", driverOpt.get());
      return ResponseEntity.ok("Login successful as Driver");
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
  }

  private ResponseEntity<String> authenticateMover(UserLoginDto userLoginDto, HttpSession session) {
    Optional<Mover> moverOpt = moverService.authenticate(userLoginDto.getPhoneNumber(), userLoginDto.getPassword());
    if (moverOpt.isPresent()) {
      session.setAttribute("user", moverOpt.get());
      return ResponseEntity.ok("Login successful as Mover");
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
  }

  @PostMapping("/register/save")
  public ResponseEntity<String> registration(@RequestBody User user, HttpSession session) {
    if (user instanceof Admin) {
      Admin admin = (Admin) user;
      if (adminService.findByPhone(admin.getPhoneNumber()) != null) {
        return ResponseEntity.badRequest().body("User already exists");
      }
      adminService.register(admin);
    } else if (user instanceof Client) {
      Client client = (Client) user;
      if (clientService.findByPhone(client.getPhoneNumber()) != null) {
        return ResponseEntity.badRequest().body("User already exists");
      }
      clientService.register(client);
    } else if (user instanceof Mover) {
      Mover mover = (Mover) user;
      if (moverService.findByPhone(mover.getPhoneNumber()) != null) {
        return ResponseEntity.badRequest().body("User already exists");
      }
      moverService.register(mover);
    } else if (user instanceof Driver) {
      Driver driver = (Driver) user;
      if (driverService.findByPhone(driver.getPhoneNumber()) != null) {
        return ResponseEntity.badRequest().body("User already exists");
      }
      driverService.register(driver);
    } else {
      return ResponseEntity.badRequest().body("Invalid role");
    }
    return ResponseEntity.ok("User added");
  }
}
