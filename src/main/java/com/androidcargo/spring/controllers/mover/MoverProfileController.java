package com.androidcargo.spring.controllers.mover;

import com.androidcargo.spring.dto.UserUpdateInfoDto;
import com.androidcargo.spring.dto.UserUpdatePasswordDto;
import com.androidcargo.spring.models.user.Mover;
import com.androidcargo.spring.service.impl.MoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/mover/profile")
public class MoverProfileController {
    private final MoverService moverService;

    @Autowired
    public MoverProfileController(MoverService moverService) {
        this.moverService = moverService;
    }

    @PatchMapping("/changeCharacteristics")
    public String changeCharacteristics(@RequestBody UserUpdateInfoDto userUpdateInfoDto, HttpSession session) {
        Mover currentMover = (Mover) session.getAttribute("currentMover");

        if (currentMover == null) {
            return "Mover not found";
        }

        moverService.updateMoverInfo(currentMover.getUserId(), userUpdateInfoDto);

        return "/admin";
    }

    @GetMapping("/changePassword")
    public String changePassword(@RequestBody UserUpdatePasswordDto userUpdatePasswordDto, HttpSession session) {
        Mover currentMover = (Mover) session.getAttribute("currentMover");

        if (currentMover == null) {
            return "Mover not found";
        }

        moverService.changePassword(currentMover.getUserId(), userUpdatePasswordDto.getPassword());

        return "/admin";
    }
}
