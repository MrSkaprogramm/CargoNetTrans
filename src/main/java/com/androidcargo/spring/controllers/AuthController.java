package com.androidcargo.spring.controllers;

import com.androidcargo.spring.dto.LoginRequest;
import com.androidcargo.spring.dto.LoginResponse;
import com.androidcargo.spring.dto.RegisterRequest;
import com.androidcargo.spring.models.admin.Admin;
import com.androidcargo.spring.models.user.*;
import com.androidcargo.spring.repository.AdminRepository;
import com.androidcargo.spring.repository.ClientRepository;
import com.androidcargo.spring.repository.DriverRepository;
import com.androidcargo.spring.repository.MoverRepository;
import com.androidcargo.spring.service.impl.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class AuthController {
  private final AdminService adminService;
  private final ClientService clientService;
  private final DriverService driverService;
  private final OrderService orderService;
  private final MoverService moverService;
  private final ClientRepository clientRepository;
  private final DriverRepository driverRepository;
  private final MoverRepository moverRepository;
    private final AdminRepository adminRepository;

    @Autowired
  public AuthController(AdminService adminService, ClientService clientService, DriverService driverService, OrderService orderService, MoverService moverService, ClientRepository repository, ClientRepository clientRepository, DriverRepository driverRepository, MoverRepository moverRepository, AdminRepository adminRepository) {
    this.adminService = adminService;
    this.clientService = clientService;
    this.driverService = driverService;
    this.orderService = orderService;
    this.moverService = moverService;
    this.clientRepository = clientRepository;
    this.driverRepository = driverRepository;
    this.moverRepository = moverRepository;
    this.adminRepository = adminRepository;
    }

  @GetMapping("/test-simple-login")
  public ResponseEntity<?> testSimpleLogin() {
    // Фиксированные тестовые данные
    String testPhone = "000000000000";
    String testPassword = "1111";

    // Пробуем авторизоваться
    LoginRequest loginRequest = new LoginRequest();
    loginRequest.setPhone(testPhone);
    loginRequest.setPassword(testPassword);

    ResponseEntity<?> loginResponse = login(loginRequest);

    // Форматируем ответ
    if (loginResponse.getStatusCode().is2xxSuccessful()) {
        Map<String, Object> body = new java.util.HashMap<>();
        body.put("message", "Успешная авторизация тестового пользователя");
        body.put("login_data", loginResponse.getBody());
        Map<String, String> value = new java.util.HashMap<>();
        value.put("phone", testPhone);
        value.put("password", testPassword);
        body.put("test_credentials", value);
        return ResponseEntity.ok()
              .body(body);
    } else {
        Map<String, Object> body = new java.util.HashMap<>();
        body.put("error", "Ошибка авторизации тестового пользователя");
        body.put("details", loginResponse.getBody());
        Map<String, String> value = new java.util.HashMap<>();
        value.put("phone", testPhone);
        value.put("password", testPassword);
        body.put("test_credentials", value);
        body.put("solution", "Убедитесь, что в БД есть пользователь с phone=000000000000 и password=1111");
        return ResponseEntity.status(loginResponse.getStatusCode())
              .body(body);
    }
  }

  @Transactional
  @GetMapping("/check-client")
  public ResponseEntity<?> checkClient() {
    Client client = new Client();
    client.setLogin("test");
    client.setPassword("test");
    client.setName("Test");
    client.setSurname("Test");
    client.setUserStatus(UserStatus.ACTIVE);

    try {
      Client saved = clientRepository.save(client);
      System.out.println("Saved client ID: " + saved.getId()); // Логируем ID
      return ResponseEntity.ok(saved);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/test")
  public ResponseEntity<String> test() {
    System.out.println("TEST ENDPOINT HIT!"); // Должно появиться в логах
    return ResponseEntity.ok("Server is working");
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    // Ищем пользователя во всех репозиториях по очереди
    Optional<? extends User> userOptional = findUserByPhone(request.getPhone());

      if (userOptional.isPresent()) {
          User user = userOptional.get();

          // Проверяем пароль
          if (!user.getPassword().equals(request.getPassword())) {
              return ResponseEntity.badRequest().body("Неверный пароль");
          }

          // Проверяем статус
          if (user.getUserStatus() != UserStatus.ACTIVE) {
              return ResponseEntity.badRequest().body("Пользователь не активен");
          }

          // Формируем ответ
          LoginResponse response = new LoginResponse();
          response.setId(user.getId());
          response.setLogin(user.getLogin());
          response.setName(user.getName());
          response.setSurname(user.getSurname());
          response.setPatronymic(user.getPatronymic());
          response.setPhoneNumber(user.getPhoneNumber());
          response.setEmail(user.getEmail());
          response.setRole(determineUserRole(user));

          return ResponseEntity.ok(response);
      } else {
          return ResponseEntity.badRequest().body("Пользователь не найден");
      }

  }

  private Optional<? extends User> findUserByPhone(String phone) {
    // Проверяем клиентов
    Optional<Client> client = clientRepository.findByPhoneNumber(phone);
    if (client.isPresent()) return client;

    // Проверяем водителей
    Optional<Driver> driver = driverRepository.findByPhoneNumber(phone);
    if (driver.isPresent()) return driver;

      Optional<Admin> admin = adminRepository.findByPhoneNumber(phone);
      if (admin.isPresent()) return admin;

    // Проверяем грузчиков
    return moverRepository.findByPhoneNumber(phone);
  }

  private String determineUserRole(User user) {
    if (user instanceof Client) return "CLIENT";
    if (user instanceof Driver) return "DRIVER";
    if (user instanceof Mover) return "LOADER";
    if (user instanceof Admin) return "ADMIN";
      return "UNKNOWN";
  }

  @PostMapping("/register")
  public ResponseEntity<?> registration(@RequestBody RegisterRequest request) {
    User user;
    // В зависимости от роли создаем конкретную сущность
    switch (request.getRole()) {
      case "CLIENT":
        Client client = new Client();
        copyUserFields(client, request);
        clientService.register(client);
        break;
      case "DRIVER":
        Driver driver = new Driver();
        copyUserFields(driver, request);
        driverService.register(driver);
        break;
      case "LOADER":
        Mover mover = new Mover();
        copyUserFields(mover, request);
        moverService.register(mover);
        break;
      default:
        return ResponseEntity.badRequest().body("Некорректная роль");
    }

    return ResponseEntity.ok("Пользователь зарегистрирован");
  }

  private void copyUserFields(User target, RegisterRequest source) {
    target.setLogin(source.getLogin());
    target.setPassword(source.getPassword());
    target.setName(source.getName());
    target.setSurname(source.getSurname());
    target.setPatronymic(source.getPatronymic());
    target.setPhoneNumber(source.getPhoneNumber()); // Добавлено
    target.setEmail(source.getEmail());             // Добавлено
    target.setUserStatus(UserStatus.ACTIVE);   // Добавлено
  }
}
