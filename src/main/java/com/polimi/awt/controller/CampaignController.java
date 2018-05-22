package com.polimi.awt.controller;

import com.polimi.awt.model.Campaign;
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
        return manager.createCampaign(request.getName());
    }
}
