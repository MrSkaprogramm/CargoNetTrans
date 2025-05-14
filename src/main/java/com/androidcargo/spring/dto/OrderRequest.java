package com.androidcargo.spring.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderRequest {
    private String address;
    private String description;
    private BigDecimal floorsQuantity;
    private BigDecimal freightQuantity;
    private boolean downtownWork;
    private boolean isElevatorDelivery;
    private boolean heavyLoad;
}
