package com.polimi.awt.payload;

import com.polimi.awt.model.Peak;

import java.util.Set;

public class PeakRequest {

//    @NotBlank
//    private Long campaignId;
    private boolean toBeAnnotated;
    private Set<Peak> peaks;

//    public Long getCampaignId() {
//        return campaignId;
//    }
//
//    public void setCampaignId(Long campaignId) {
//        this.campaignId = campaignId;
//    }

    public boolean isToBeAnnotated() {
        return toBeAnnotated;
    }

    public void setToBeAnnotated(boolean toBeAnnotated) {
        this.toBeAnnotated = toBeAnnotated;
    }

    public Set<Peak> getPeaks() {
        return peaks;
    }

    public void setPeaks(Set<Peak> peaks) {
        this.peaks = peaks;
    }
}
