package com.polimi.awt.model;

public class User {

    private Long id;
    private String username;
    private String password;
    private String emailAddress;
    private Role role;

    public User(Long id, String username, String password, String emailAddress, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
