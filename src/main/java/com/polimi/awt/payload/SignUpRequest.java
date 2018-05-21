package com.polimi.awt.payload;

import com.polimi.awt.model.RoleName;

import javax.validation.constraints.NotBlank;

public class SignUpRequest {


    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String emailAddress;

    @NotBlank
    private String role;

    public RoleName roleToRoleName () {
        if (hasRoleManager()) {
            return RoleName.MANAGER;
        }
        else return RoleName.WORKER;
    }
    public boolean hasRoleManager (){
        return (this.role.equals("MANAGER") ||
                this.role.equals("Manager") || this.role.equals("manager"));
    }

    public boolean hasRoleWorker (){
        return (this.role.equals("WORKER") ||
                this.role.equals("Worker") || this.role.equals("worker"));
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
