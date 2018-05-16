package com.polimi.awt.model;

import java.util.List;

public class Worker extends User {

    private List <Campaign> enrolledCampaigns;

    public Worker(String username, String password, String emailAddress, boolean isManager) {
        super(username, password, emailAddress, isManager);
    }
}
