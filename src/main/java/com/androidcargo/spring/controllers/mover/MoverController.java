package com.androidcargo.spring.controllers.mover;

import com.androidcargo.spring.service.impl.MoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/mover")
public class MoverController {
    private final MoverService moverService;

    @Autowired
    public MoverController(MoverService moverService) {
        this.moverService = moverService;
    }

    @GetMapping("/joinTheLine")
    public String joinTheLine(HttpSession session) {
        return "/admin";
    }

    @GetMapping("/takeToWork")
    public String takeToWork(HttpSession session) {
        return "/admin";
    }

    @GetMapping("/changeWorkList")
    public String changeWorkList(HttpSession session) {
        return "/admin";
    }
}
