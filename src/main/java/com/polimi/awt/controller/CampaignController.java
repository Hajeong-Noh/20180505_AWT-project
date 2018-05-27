package com.polimi.awt.controller;

import com.polimi.awt.model.Campaign;
import com.polimi.awt.model.users.Manager;
import com.polimi.awt.model.users.Worker;
import com.polimi.awt.payload.CampaignRequest;
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

    @GetMapping("/campaigns/{campaignId}")
    private Campaign getCampaignById (@PathVariable Long campaignId) {
       return campaignRepository.findCampaignById(campaignId);
    }

    @PostMapping("/campaigns")
    private Campaign createCampaign(@RequestBody CampaignRequest request) {
        Manager manager = (Manager) userRepository.findUserById(request.getManagerId());
        return campaignRepository.save(manager.createCampaign(request.getName()));
    }

    @PatchMapping("/campaigns/{campaignId}")
    private Campaign updateCampaignStatus (@PathVariable Long campaignId, @RequestBody CampaignRequest request) {
        Manager manager = (Manager) userRepository.findUserById(request.getManagerId());
        Campaign campaign = campaignRepository.findCampaignById(campaignId);
        return campaignRepository.save(manager.updateCampaignStatus(campaign));
    }

    @PostMapping("/campaigns/{campaignId}")
    private void enrollInCampaign(@PathVariable Long campaignId, @RequestBody CampaignRequest request) {
        Worker worker = (Worker) userRepository.findUserById(request.getWorkerId());
        Campaign campaign = campaignRepository.findCampaignById(campaignId);
        campaignRepository.save(worker.enrollInCampaign(campaign));
    }
}
