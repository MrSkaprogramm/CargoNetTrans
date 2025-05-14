package com.androidcargo.spring.dto;

public class UpdateProfileRequest {
    private String currentPhone;  // изменили с currentEmail на currentPhone
    private String name;
    private String newEmail;

    public UpdateProfileRequest() {
    }

    public UpdateProfileRequest(String currentPhone, String name, String newEmail) {
        this.currentPhone = currentPhone;
        this.name = name;
        this.newEmail = newEmail;
    }

    // Геттеры и сеттеры
    public String getCurrentPhone() {
        return currentPhone;
    }

    public void setCurrentPhone(String currentPhone) {
        this.currentPhone = currentPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }
}
