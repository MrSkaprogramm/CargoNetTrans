package com.androidcargo.spring.controllers.client;

import com.androidcargo.spring.service.impl.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/joinTheLine")
    public String joinTheLine(HttpSession session) {
        return "/admin";
    }

    @GetMapping("/takeToWork")
    public String takeToWork(HttpSession session) {
        return "/admin";
    }

    @GetMapping("/cancelOrder")
    public String cancelOrder(HttpSession session) {
        return "/admin";
    }

    @GetMapping("/changeOrder")
    public String changeOrder(HttpSession session) {
        return "/admin";
    }

    @GetMapping("/showWorkContractorCharacteristics")
    public String showWorkContractorCharacteristics() {
        return "/admin";
    }
}
