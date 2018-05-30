package com.polimi.awt.controller;

import com.polimi.awt.model.Campaign;
import com.polimi.awt.model.Peak;
import com.polimi.awt.repository.CampaignRepository;
import com.polimi.awt.repository.PeakRepository;
import com.polimi.awt.security.CurrentUser;
import com.polimi.awt.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class PeakController {
    @Autowired
    private PeakRepository peakRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    @GetMapping("/campaigns/{campaignId}/peaks")
    private Set<Peak> getPeaksForCampaign(@PathVariable Long campaignId) {
        return peakRepository.findAllByCampaignId(campaignId);
    }

    @GetMapping("/campaigns/{campaignId}/peaks/{peakId}")
    private Peak findPeakById(@PathVariable Long peakId) {
        return peakRepository.findPeakById(peakId);
    }

    @PostMapping("/campaigns/{campaignId}/peaks")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity uploadPeakData(@CurrentUser UserPrincipal currentUser, @RequestBody Set<Peak> peaks, @PathVariable Long campaignId, @RequestParam boolean annotate) {

        Campaign campaign = campaignRepository.findCampaignById(campaignId);
        if (campaign.getManager().getId().equals(currentUser.getId())) {

            campaign.addPeaks(peaks, annotate);
            peakRepository.saveAll(peaks);
            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully");
        }
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
                    body("You are not authorized to upload peaks for this campaign.");
    }

    @PatchMapping("/campaigns/{campaignId}/peaks/{peakId}")
    @PreAuthorize("hasAuthority('MANAGER')")
    private ResponseEntity setToBeAnnotated(@CurrentUser UserPrincipal currentUser, @PathVariable Long peakId) {
        Peak peak = peakRepository.findPeakById(peakId);
        if (peak.getCampaign().getManager().getId().equals(currentUser.getId())) {

            try {
                peak.inverseToBeAnnotated();
            } catch (RuntimeException e) {//TODO: add custom exception
                return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
            }
            peakRepository.save(peak);
            return ResponseEntity.status(HttpStatus.OK).body("It worked");
        }
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
                    body("You are not authorized to edit this peak.");


    }


}
