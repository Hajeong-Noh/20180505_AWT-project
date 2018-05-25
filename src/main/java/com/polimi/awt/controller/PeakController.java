package com.polimi.awt.controller;

import com.polimi.awt.model.Campaign;
import com.polimi.awt.model.Peak;
import com.polimi.awt.payload.PeakRequest;
import com.polimi.awt.repository.CampaignRepository;
import com.polimi.awt.repository.PeakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class PeakController {
    @Autowired
    private PeakRepository peakRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    @PostMapping("/peaks")
    private String uploadPeakData (@RequestBody PeakRequest peakRequest) {

        Set <Peak> peaks = peakRequest.getPeaks();
        Campaign campaign = campaignRepository.findCampaignById(peakRequest.getCampaignId());
        campaign.addPeaks(peaks, peakRequest.isToBeAnnotated());
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
    private void setToBeAnnotated(@PathVariable Long peakId) {
        Peak peak = peakRepository.findPeakById(peakId);
        peak.inverseToBeAnnotated();
        peakRepository.save(peak);
    }







}
