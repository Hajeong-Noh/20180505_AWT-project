package com.polimi.awt.controller;

import com.polimi.awt.model.Campaign;
import com.polimi.awt.model.CampaignStatus;
import com.polimi.awt.model.users.Manager;
import com.polimi.awt.payload.CreateCampaignRequest;
import com.polimi.awt.repository.CampaignRepository;
import com.polimi.awt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CampaignController {
    @Autowired
    private CampaignRepository campaignRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/campaigns")
    private List<Campaign> getCampaigns() {
        return campaignRepository.findAll();
    }

    @PostMapping("/campaigns")
    private Campaign createCampaign(@RequestBody CreateCampaignRequest request) {
        Manager manager = (Manager) userRepository.findUserById(request.getId());
        return campaignRepository.save(manager.createCampaign(request.getName()));
    }

    @PatchMapping("/campaigns/{campaignId}")
    private Campaign updateCampaignStatus (@PathVariable Long campaignId) {
        Campaign campaign = campaignRepository.findCampaignById(campaignId);
        if (campaign.getCampaignStatus().equals(CampaignStatus.CREATED)) {
            campaign.setCampaignStatus(CampaignStatus.STARTED);
        }
        else if (campaign.getCampaignStatus().equals(CampaignStatus.STARTED)) {
            campaign.setCampaignStatus(CampaignStatus.CLOSED);
        }
        else if (campaign.getCampaignStatus().equals(CampaignStatus.CLOSED)) {
                throw new RuntimeException("The campaign is already closed.");
            }
        //TODO: set startDate and endDate when updated
        return campaignRepository.save(campaign);
    }
}
