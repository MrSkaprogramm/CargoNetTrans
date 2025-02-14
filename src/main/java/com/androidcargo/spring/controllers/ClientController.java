package com.androidcargo.spring.controllers;

import com.androidcargo.spring.service.impl.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/showWorkContractorCharacteristics")
    public String showWorkContractorCharacteristics() {
        //выгрузка из бд данных о выбранном исполнителе заказа
        return "/admin";
    }
}
