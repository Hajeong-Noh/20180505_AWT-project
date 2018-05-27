package com.polimi.awt.model.users;

import com.polimi.awt.model.Campaign;
import com.polimi.awt.model.CampaignStatus;
import com.polimi.awt.model.Role;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
public class Manager extends User {

    public Manager() {
    }

    public Manager(String username, String password, String emailAddress, Role role) {
        super(username, password, emailAddress, role);
    }

    public Manager(String username, String password, String emailAddress) {
        super(username, password, emailAddress);
    }

    public Campaign createCampaign(String name){
        Campaign newCampaign = new Campaign();
        newCampaign.setName(name);
        newCampaign.setCampaignStatus(CampaignStatus.CREATED);
        newCampaign.setManager(this);
        return newCampaign;
    }

    public Campaign udpateCampaignStatus (Campaign campaign) {
        if (campaign.getCampaignStatus().equals(CampaignStatus.CREATED)) {
            campaign.setCampaignStatus(CampaignStatus.STARTED);
            campaign.setStartDate(LocalDateTime.now(ZoneId.of("Europe/Rome")));
        }
        else if (campaign.getCampaignStatus().equals(CampaignStatus.STARTED)) {
            campaign.setCampaignStatus(CampaignStatus.CLOSED);
            campaign.setEndDate(LocalDateTime.now(ZoneId.of("Europe/Rome")));
        }
        else if (campaign.getCampaignStatus().equals(CampaignStatus.CLOSED)) {
                throw new RuntimeException("The campaign is already closed.");
            }
        return campaign;
    }

}
