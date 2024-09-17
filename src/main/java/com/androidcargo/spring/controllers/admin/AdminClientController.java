package com.androidcargo.spring.controllers.admin;

import com.androidcargo.spring.service.impl.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/showClients")
public class AdminClientController {
    private final ClientService clientService;

    @Autowired
    public AdminClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}/showClientInfo")
    public String showClientInfo(@PathVariable int id) {
        clientService.getClient(id);
        return "/admin";
    };

    @GetMapping("/{id}/showOrders")
    public String showOrders(@PathVariable int id) {
        clientService.getClientOrders(id);
        return "/admin";
    };

    @GetMapping("/{id}/blockClient")
    public String blockClient(@PathVariable int id) {
        clientService.blockClient(id);
        return "/admin";
    };

    @GetMapping("/{id}/unBlockClient")
    public String unBlockClient(@PathVariable int id) {
        clientService.unBlockClient(id);
        return "/admin";
    };
}
