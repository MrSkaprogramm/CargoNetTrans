package com.androidcargo.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateInfoDto {
    private String login;
    private String name;
    private String surname;
    private String patronymic;
    private String phoneNumber;
    private String email;
}
