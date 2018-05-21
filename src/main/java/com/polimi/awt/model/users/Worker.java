package com.polimi.awt.model.users;

import com.polimi.awt.model.Campaign;
import com.polimi.awt.model.Role;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Worker extends User {



    public Worker() {
    }

    public Worker(String username,String password, String emailAddress, Role role) {
        super(username, password, emailAddress, role);
    }

    public Worker(String username, String password, String emailAddress) {
        super(username, password, emailAddress);
    }

    @ManyToMany
    private Set <Campaign> enrolledCampaigns = new HashSet<>();
}
