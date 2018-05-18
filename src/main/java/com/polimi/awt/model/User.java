package com.polimi.awt.model;

public abstract class User {

    private Long id;
    private String username;
    private String password;
    private String emailAddress;
    private boolean isManager;

    public User(String username, String password, String emailAddress, boolean isManager) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.isManager = isManager;
    }
}
