package com.androidcargo.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private int id;
    private String login;
    private String name;
    private String surname;
    private String patronymic;
    private String phoneNumber;
    private String email;
    private String role;
}
