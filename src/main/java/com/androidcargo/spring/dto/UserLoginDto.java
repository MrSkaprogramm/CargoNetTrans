package com.androidcargo.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDto {
    private String phoneNumber;
    private String password;
    private String role;
}
