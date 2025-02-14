package com.androidcargo.spring.controllers;

import com.androidcargo.spring.service.impl.MoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mover")
public class MoverController {
    private final MoverService moverService;

    @Autowired
    public MoverController(MoverService moverService) {
        this.moverService = moverService;
    }

    @GetMapping("/changeWorkList")
    public boolean changeWorkList() {
        //передача в бд новых данных о выполняемых работах
        return true;
    }
}
