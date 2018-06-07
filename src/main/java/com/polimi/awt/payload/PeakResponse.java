package com.polimi.awt.payload;

public class PeakResponse {

    private Long peakId;
    private String PeakName;

    public PeakResponse(Long peakId, String peakName) {
        this.peakId = peakId;
        PeakName = peakName;
    }

    public Long getPeakId() {
        return peakId;
    }

    public void setPeakId(Long peakId) {
        this.peakId = peakId;
    }

    public String getPeakName() {
        return PeakName;
    }

    public void setPeakName(String peakName) {
        PeakName = peakName;
    }
}
