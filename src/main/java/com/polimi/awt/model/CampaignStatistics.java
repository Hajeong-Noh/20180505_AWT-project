package com.polimi.awt.model;


public class CampaignStatistics {

    private int numberOfStartedPeaks;
    private int numberOfAnnotatedPeaks;
    private int numberOfRejectedAnnotations;
    private int numberOfConflicts;

    public CampaignStatistics(int numberOfStartedPeaks, int numberOfAnnotatedPeaks, int numberOfRejectedAnnotations, int numberOfConflicts) {
        this.numberOfStartedPeaks = numberOfStartedPeaks;
        this.numberOfAnnotatedPeaks = numberOfAnnotatedPeaks;
        this.numberOfRejectedAnnotations = numberOfRejectedAnnotations;
        this.numberOfConflicts = numberOfConflicts;
    }

    public int getNumberOfStartedPeaks() {
        return numberOfStartedPeaks;
    }

    public void setNumberOfStartedPeaks(int numberOfStartedPeaks) {
        this.numberOfStartedPeaks = numberOfStartedPeaks;
    }

    public int getNumberOfAnnotatedPeaks() {
        return numberOfAnnotatedPeaks;
    }

    public void setNumberOfAnnotatedPeaks(int numberOfAnnotatedPeaks) {
        this.numberOfAnnotatedPeaks = numberOfAnnotatedPeaks;
    }

    public int getNumberOfRejectedAnnotations() {
        return numberOfRejectedAnnotations;
    }

    public void setNumberOfRejectedAnnotations(int numberOfRejectedAnnotations) {
        this.numberOfRejectedAnnotations = numberOfRejectedAnnotations;
    }

    public int getNumberOfConflicts() {
        return numberOfConflicts;
    }

    public void setNumberOfConflicts(int numberOfConflicts) {
        this.numberOfConflicts = numberOfConflicts;
    }
}

