package com.polimi.awt.payload;

import com.polimi.awt.model.RoleName;

import javax.validation.constraints.NotBlank;

public class SignupRequest {


    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String emailAddress;

    @NotBlank
    private String role;

    public RoleName roleToRoleName () {
        if (this.role.equals("MANAGER")) {
            return RoleName.MANAGER;
        }
        else return RoleName.WORKER;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
