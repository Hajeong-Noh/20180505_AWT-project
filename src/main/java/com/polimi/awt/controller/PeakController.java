package com.polimi.awt.controller;

import com.polimi.awt.exception.PreconditionFailedException;
import com.polimi.awt.exception.UnauthorizedException;
import com.polimi.awt.model.Campaign;
import com.polimi.awt.model.CampaignStatus;
import com.polimi.awt.model.Peak;
import com.polimi.awt.payload.HttpResponseStatus.ApiResponse;
import com.polimi.awt.payload.HttpResponseStatus.CreatedResponse;
import com.polimi.awt.payload.HttpResponseStatus.OkResponse;
import com.polimi.awt.repository.CampaignRepository;
import com.polimi.awt.repository.PeakRepository;
import com.polimi.awt.security.CurrentUser;
import com.polimi.awt.security.UserPrincipal;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
@Api(description="Operations related to Peaks", tags = "Peaks")
public class PeakController {
    @Autowired
    private PeakRepository peakRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    @GetMapping("/campaigns/{campaignId}/peaks")
    public Set<Peak> getPeaksForCampaign(@PathVariable Long campaignId) {

        return peakRepository.findAllByCampaignId(campaignId);
    }

    @GetMapping("/campaigns/{campaignId}/peaks/{peakId}")
    public Peak findPeakById(@PathVariable Long peakId) {

        return peakRepository.findPeakById(peakId);
    }

    @PostMapping("/campaigns/{campaignId}/peaks")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ApiResponse uploadPeakData(@CurrentUser UserPrincipal currentUser, @RequestBody Set<Peak> peaks,
                                      @PathVariable Long campaignId, @RequestParam boolean annotate) {

        Campaign campaign = campaignRepository.findCampaignById(campaignId);
        if (!campaign.getManager().getId().equals(currentUser.getId())) {
            throw new UnauthorizedException();
        }

        if (!campaign.getCampaignStatus().equals(CampaignStatus.CREATED)) {
            throw new PreconditionFailedException("You cannot upload peaks for this campaign anymore.");
        }

        campaign.addPeaks(peaks, annotate);
        peakRepository.saveAll(peaks);
        return new CreatedResponse("File uploaded successfully.");
    }

    @PatchMapping("/campaigns/{campaignId}/peaks/{peakId}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ApiResponse setToBeAnnotated(@CurrentUser UserPrincipal currentUser, @PathVariable Long peakId) {
        Peak peak = peakRepository.findPeakById(peakId);

        if (!peak.getCampaign().getManager().getId().equals(currentUser.getId())) {
            throw new UnauthorizedException();
        }
        peak.inverseToBeAnnotated();//Throws exception if status not Created
        peakRepository.save(peak);
        return new OkResponse();
    }
}
