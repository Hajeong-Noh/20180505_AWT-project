package com.polimi.awt.controller;

import com.polimi.awt.model.Campaign;
import com.polimi.awt.model.users.Manager;
import com.polimi.awt.model.users.User;
import com.polimi.awt.model.users.Worker;
import com.polimi.awt.payload.CampaignRequest;
import com.polimi.awt.repository.CampaignRepository;
import com.polimi.awt.repository.UserRepository;
import com.polimi.awt.security.CurrentUser;
import com.polimi.awt.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.polimi.awt.model.RoleName.MANAGER;

@RestController
@RequestMapping("/api")
public class CampaignController {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/campaigns")
    public List<Campaign> getCampaigns(@CurrentUser UserPrincipal currentUser) {
        User user = userRepository.findUserById(currentUser.getId());
        if (user.rolesContainsRoleName(MANAGER)) {
            return campaignRepository.findAllByManager_Id(user.getId());
        }
        //TODO: return enrolled Campaigns if user is a worker
        return campaignRepository.findAll();
    }

    @GetMapping("/campaigns/{campaignId}")
    public Campaign getCampaignById (@PathVariable Long campaignId, @CurrentUser UserPrincipal currentUser) {
        Campaign campaign = campaignRepository.findCampaignById(campaignId);
        if (campaign.getManager().getId().equals(currentUser.getId())) {

        }
        return campaignRepository.findCampaignById(campaignId);
    }

    @PostMapping("/campaigns")
    @PreAuthorize("hasAuthority('MANAGER')")
    public Campaign createCampaign(@RequestBody CampaignRequest request, @CurrentUser UserPrincipal currentUser) {
        Manager manager = (Manager) userRepository.findUserById(currentUser.getId());
        return campaignRepository.save(manager.createCampaign(request.getName()));
    }

    @PatchMapping("/campaigns/{campaignId}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public Campaign updateCampaignStatus (@PathVariable Long campaignId, @RequestBody CampaignRequest request) {
        Manager manager = (Manager) userRepository.findUserById(request.getManagerId());
        Campaign campaign = campaignRepository.findCampaignById(campaignId);
        return campaignRepository.save(manager.updateCampaignStatus(campaign));
    }

    @PostMapping("/campaigns/{campaignId}")
    @PreAuthorize("hasAuthority('WORKER')")
    public void enrollInCampaign(@PathVariable Long campaignId, @RequestBody CampaignRequest request) {
        Worker worker = (Worker) userRepository.findUserById(request.getWorkerId());
        Campaign campaign = campaignRepository.findCampaignById(campaignId);
        campaignRepository.save(worker.enrollInCampaign(campaign));
    }
}
