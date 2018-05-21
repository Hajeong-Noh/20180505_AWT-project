package com.polimi.awt.model.users;

import com.polimi.awt.model.Campaign;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Manager extends User {

    public Manager(String username, String password, String emailAddress) {
        super(username, password, emailAddress);
    }
    @OneToMany(mappedBy = "manager", fetch = FetchType.EAGER)
    private Set <Campaign> managedCampaigns = new HashSet<>();
}
