package com.polimi.awt.model.users;

import com.polimi.awt.model.Campaign;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Worker extends User {

    public Worker(String username, String password, String emailAddress) {
        super(username, password, emailAddress);
    }
    @ManyToMany
    private Set <Campaign> enrolledCampaigns = new HashSet<>();
}
