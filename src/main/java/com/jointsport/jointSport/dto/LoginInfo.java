package com.jointsport.jointSport.dto;

import javax.validation.constraints.NotEmpty;

public class LoginInfo {

    public LoginInfo() {
    }
    @NotEmpty(message = "Email should not be empty")
    private String email;
    private String password;


    public LoginInfo(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
