package com.androidcargo.spring.controllers.client;
import com.androidcargo.spring.dto.UserUpdateInfoDto;
import com.androidcargo.spring.dto.UserUpdatePasswordDto;
import com.androidcargo.spring.models.user.Client;
import com.androidcargo.spring.service.impl.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/client/profile")
public class ClientProfileController {
    private final ClientService clientService;

    @Autowired
    public ClientProfileController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/changeCharacteristics")
    public String updateInfo(@RequestBody UserUpdateInfoDto userUpdateInfoDto, HttpSession session) {
        Client currentClient = (Client) session.getAttribute("currentClient");

        if (currentClient == null) {
            return "Client not found";
        }

        clientService.updateClientInfo(currentClient.getUserId(), userUpdateInfoDto);

        return "/admin";
    }

    @GetMapping("/changePassword")
    public String changePassword(@RequestBody UserUpdatePasswordDto userUpdatePasswordDto, HttpSession session) {
        Client currentClient = (Client) session.getAttribute("currentClient");

        if (currentClient == null) {
            return "Client not found";
        }

        clientService.changePassword(currentClient.getUserId(), userUpdatePasswordDto.getPassword());

        return "/admin";
    }
}
