package com.polimi.awt.controller;

import com.polimi.awt.model.Campaign;
import com.polimi.awt.model.Peak;
import com.polimi.awt.repository.CampaignRepository;
import com.polimi.awt.repository.PeakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class PeakController {
    @Autowired
    private PeakRepository peakRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    @PostMapping("/campaigns/{campaignId}/peaks")
    private String uploadPeakData (@RequestBody Set <Peak> peaks, @PathVariable Long campaignId, @RequestParam boolean annotate) {

        //Set <Peak> peaks = peakRequest.getPeaks();
        Campaign campaign = campaignRepository.findCampaignById(campaignId);
        campaign.addPeaks(peaks, annotate);
        peakRepository.saveAll(peaks);
        //TODO: set real response
        return ("Successful");
    }

    @GetMapping("/campaigns/{campaignId}/peaks")
    private Set <Peak> getPeaksForCampaign (@PathVariable Long campaignId) {
        return peakRepository.findAllByCampaignId(campaignId);
    }

    @GetMapping("/campaigns/{campaignId}/peaks/{peakId}")
    private Peak findPeakById(@PathVariable Long peakId) {
       return peakRepository.findPeakById(peakId);
    }

    @PatchMapping ("/campaigns/{campaignId}/peaks/{peakId}")
    private ResponseEntity setToBeAnnotated(@PathVariable Long peakId) {
        Peak peak = peakRepository.findPeakById(peakId);
        try {
            peak.inverseToBeAnnotated();
        } catch (RuntimeException e) {//TODO: add custom exception
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
        }
        peakRepository.save(peak);
        return ResponseEntity.status(HttpStatus.OK).body("It worked");
    }







}
