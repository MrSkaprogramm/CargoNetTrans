package com.androidcargo.spring.controllers;

import com.androidcargo.spring.dto.UserLoginDto;
import com.androidcargo.spring.models.user.User;
import com.androidcargo.spring.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class AuthController {
  private final UserService userService;
  @Autowired
  public AuthController(UserService userService, AdminService adminService, ClientService clientService, DriverService driverService, MoverService moverService) {
    this.userService = userService;
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody UserLoginDto userLoginDto) {
    Optional<User> adminOpt = userService.authenticate(userLoginDto.getPhoneNumber(), userLoginDto.getPassword());
    if (adminOpt.isPresent()) {
      session.setAttribute("user", adminOpt.get());
      return ResponseEntity.ok("Login successful");
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
  }

  @PostMapping("/register/save")
  public ResponseEntity<String> registration(@RequestBody User user) {
    if (userService.findByPhone(user.getPhoneNumber()) != null) {
      return ResponseEntity.badRequest().body("User already exists");
    }
    userService.register(user);
    return ResponseEntity.ok("User added");
  }
}
