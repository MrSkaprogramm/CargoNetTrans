package com.androidcargo.spring.controllers.admin;

import com.androidcargo.spring.service.impl.MoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/showMovers")
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

    @GetMapping("/{id}/blockMover")
    public String blockMover(@PathVariable int id) {
        moverService.blockMover(id);
        return "/admin";
    };

    @GetMapping("/{id}/unBlockMover")
    public String unBlockMover(@PathVariable int id) {
        moverService.unBlockMover(id);
        return "/admin";
    };
}
