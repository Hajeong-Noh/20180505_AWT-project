package com.polimi.awt.model;

public class PeakConflict {

    private Long peakId;
    private String peakName;
    private int numberOfAnnotationsWithValidPeak;
    private int numberOfAnnotationsWithInvalidPeak;

    public PeakConflict(Long peakId, String peakName, int numberOfAnnotationsWithValidPeak, int numberOfAnnotationsWithInvalidPeak) {
        this.peakId = peakId;
        this.peakName = peakName;
        this.numberOfAnnotationsWithValidPeak = numberOfAnnotationsWithValidPeak;
        this.numberOfAnnotationsWithInvalidPeak = numberOfAnnotationsWithInvalidPeak;
    }

    public Long getPeakId() {
        return peakId;
    }

    public void setPeakId(Long peakId) {
        this.peakId = peakId;
    }

    public String getPeakName() {
        return peakName;
    }

    public void setPeakName(String peakName) {
        this.peakName = peakName;
    }

    public int getNumberOfAnnotationsWithValidPeak() {
        return numberOfAnnotationsWithValidPeak;
    }

    public void setNumberOfAnnotationsWithValidPeak(int numberOfAnnotationsWithValidPeak) {
        this.numberOfAnnotationsWithValidPeak = numberOfAnnotationsWithValidPeak;
    }

    public int getNumberOfAnnotationsWithInvalidPeak() {
        return numberOfAnnotationsWithInvalidPeak;
    }

    public void setNumberOfAnnotationsWithInvalidPeak(int numberOfAnnotationsWithInvalidPeak) {
        this.numberOfAnnotationsWithInvalidPeak = numberOfAnnotationsWithInvalidPeak;
    }
}
