package com.polimi.awt.model.users;

import com.polimi.awt.model.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

    public Annotation createAnnotation(Peak peak, boolean isValid, Double elevation, String name, Set<LocalizedPeakName> localizedPeakNames){
        Annotation newAnnotation = new Annotation();
        newAnnotation.setCreationDateTime(LocalDateTime.now(ZoneId.of("Europe/Rome")));
        newAnnotation.setPeak(peak);
        newAnnotation.setValid(isValid);
        newAnnotation.setElevation(elevation);
        newAnnotation.setName(name);
        newAnnotation.setLocalizedPeakNames(localizedPeakNames);
        return newAnnotation;
    }
}