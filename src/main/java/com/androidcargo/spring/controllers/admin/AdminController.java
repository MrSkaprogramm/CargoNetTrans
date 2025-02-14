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
        //отправка в бд изменённых данных
        return "/admin";
    }

    @GetMapping("/changeAdminPermission")
    public String changeAdminPermission() {
        //отправка в бд изменённых данных
        return "/admin";
    }
}
