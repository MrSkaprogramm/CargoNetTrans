package com.androidcargo.spring.controllers.admin;

import com.androidcargo.spring.service.impl.MoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AdminMoverController {
    private final MoverService moverService;

    @Autowired
    public AdminMoverController(MoverService moverService) {
        this.moverService = moverService;
    }

    @GetMapping("/{id}/showMoverInfo")
    public String showMoverInfo(@PathVariable int id) {
        moverService.getMover(id);
        return "/admin";
    };

    @GetMapping("/{id}/showCarInfo")
    public String showOrders(@PathVariable int id) {
        moverService.getMoverOrders(id);
        return "/admin";
    };

    @GetMapping("/{id}/showCarInfo")
    public String showMoverData(@PathVariable int id) {
        carService.getCar(id);
        return "/admin";
    };

    @GetMapping("/changeWorkPrices")
    public String changeWorkPrices() {
        //отправка в бд изменённых данных о ценах на работы
        return "/admin";
    }
}
