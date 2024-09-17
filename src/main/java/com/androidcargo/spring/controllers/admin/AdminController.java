package com.androidcargo.spring.controllers.admin;

import com.androidcargo.spring.service.impl.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/changeCharacteristics")
    public String changeCharacteristics() {
        return "/admin";
    }

    @GetMapping("/changeAdminPermission")
    public String changeAdminPermission() {
        return "/admin";
    }

    @GetMapping("/changeWorkPrices")
    public String changeWorkPrices() {
        return "/admin";
    }

    @GetMapping("/saveWorkPrices")
    public String saveWorkPrices() {
        return "/admin";
    }
}
