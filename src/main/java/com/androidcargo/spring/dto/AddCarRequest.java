package com.androidcargo.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCarRequest {
    private String bodyType;
    private double capacity;
    private double width;
    private double height;
    private String driverPhone;
}
