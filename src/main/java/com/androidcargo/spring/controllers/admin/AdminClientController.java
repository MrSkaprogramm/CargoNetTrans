package com.androidcargo.spring.controllers.admin;

import com.androidcargo.spring.models.order.Order;
import com.androidcargo.spring.models.user.Client;
import com.androidcargo.spring.service.impl.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class AdminClientController {
    private final ClientService clientService;

    @Autowired
    public AdminClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/{id}/showClientInfo")
    public Client showClientInfo(@PathVariable int id) {
        return clientService.getClient(id);
    };

    @GetMapping("/{id}/showOrders")
    public List<Order> showOrders(@PathVariable int id) {
        return clientService.getClientOrders(id);
    };

    @GetMapping("/{id}/showCarInfo")
    public List<Client> showClients(@PathVariable int id) {
        return clientService.getAllClients();
    };
}
