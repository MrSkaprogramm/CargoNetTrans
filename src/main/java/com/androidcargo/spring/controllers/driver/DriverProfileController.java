package com.androidcargo.spring.controllers.driver;

import com.androidcargo.spring.dto.UserUpdateInfoDto;
import com.androidcargo.spring.dto.UserUpdatePasswordDto;
import com.androidcargo.spring.models.user.Driver;
import com.androidcargo.spring.service.impl.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/driver/profile")
public class DriverProfileController {
    private final DriverService driverService;

    @Autowired
    public DriverProfileController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PatchMapping("/changeCharacteristics")
    public String changeInfo(@RequestBody UserUpdateInfoDto userUpdateInfoDto, HttpSession session) {
        Driver currentDriver = (Driver) session.getAttribute("currentDriver");

        if (currentDriver == null) {
            return "Driver not found";
        }

        driverService.updateDriverInfo(currentDriver.getUserId(), userUpdateInfoDto);

        return "/admin";
    }

    @GetMapping("/changePassword")
    public String changePassword(@RequestBody UserUpdatePasswordDto userUpdatePasswordDto, HttpSession session) {
        Driver currentDriver = (Driver) session.getAttribute("currentDriver");

        if (currentDriver == null) {
            return "Driver not found";
        }

        // Вызываем метод сервиса для изменения пароля
        driverService.changePassword(currentDriver.getUserId(), userUpdatePasswordDto.getPassword());

        return "/admin";
    }
}
