package com.polimi.awt.model;

import java.util.List;

public class Manager extends User {

    private List <Campaign> campaigns;

    public Manager(String username, String password, String emailAddress, boolean isManager) {
        super(username, password, emailAddress, isManager);
    }
}
