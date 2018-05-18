package com.polimi.awt.controller;

import com.polimi.awt.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CampaignController {
    @Autowired
    private CampaignRepository campaignRepository;
}
