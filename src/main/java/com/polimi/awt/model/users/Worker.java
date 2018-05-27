package com.polimi.awt.model.users;

import com.polimi.awt.model.Annotation;
import com.polimi.awt.model.Campaign;
import com.polimi.awt.model.LocalizedPeakName;
import com.polimi.awt.model.Role;

import javax.persistence.CascadeType;
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

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set <Campaign> enrolledCampaigns = new HashSet<>();

    public Campaign enrollInCampaign(Campaign campaign){ enrolledCampaigns.add(campaign); return campaign; }

    public Annotation createAnnotation(Boolean isValid, Double elevation, String name, Set<LocalizedPeakName> localizedPeakNames){
        Annotation newAnnotation = new Annotation();
        newAnnotation.setValid(isValid);
        newAnnotation.setElevation(elevation);
        newAnnotation.setLocalizedPeakNames(localizedPeakNames);
        return newAnnotation;
    }
}
