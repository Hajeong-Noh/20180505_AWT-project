package com.polimi.awt.controller;

import com.polimi.awt.model.Campaign;
import com.polimi.awt.model.users.Manager;
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
    private Campaign createCampaign(@RequestParam (value = "id") Long managerId, @RequestParam (value = "name") String name) {
        Manager manager = (Manager) userRepository.findUserById(managerId);
        return manager.createCampaign(name);
    }
}
