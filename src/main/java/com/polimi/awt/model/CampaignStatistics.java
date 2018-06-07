package com.polimi.awt.model;


import com.polimi.awt.payload.PeakResponse;

import java.util.List;

public class CampaignStatistics {

    private int numberOfStartedPeaks;
    private int numberOfAnnotatedPeaks;
    private int numberOfPeaksWithRejectedAnnotations;
    private int numberOfConflicts;

    private List<PeakResponse> annotatedPeaks;
    private List<PeakResponse> annotatedPeaksWithRejectedAnnotations;
    private List<PeakConflict> conflicts;


    public CampaignStatistics(int numberOfStartedPeaks, int numberOfAnnotatedPeaks, int numberOfPeaksWithRejectedAnnotations, int numberOfConflicts,
                              List<PeakResponse> annotatedPeaks, List<PeakResponse> annotatedPeaksWithRejectedAnnotations,
                              List<PeakConflict> conflicts) {
        this.numberOfStartedPeaks = numberOfStartedPeaks;
        this.numberOfAnnotatedPeaks = numberOfAnnotatedPeaks;
        this.numberOfPeaksWithRejectedAnnotations = numberOfPeaksWithRejectedAnnotations;
        this.numberOfConflicts = numberOfConflicts;
        this.annotatedPeaks = annotatedPeaks;
        this.annotatedPeaksWithRejectedAnnotations = annotatedPeaksWithRejectedAnnotations;
        this.conflicts = conflicts;
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

    public int getNumberOfPeaksWithRejectedAnnotations() {
        return numberOfPeaksWithRejectedAnnotations;
    }

    public void setNumberOfPeaksWithRejectedAnnotations(int numberOfPeaksWithRejectedAnnotations) {
        this.numberOfPeaksWithRejectedAnnotations = numberOfPeaksWithRejectedAnnotations;
    }

    public int getNumberOfConflicts() {
        return numberOfConflicts;
    }

    public void setNumberOfConflicts(int numberOfConflicts) {
        this.numberOfConflicts = numberOfConflicts;
    }

    public List<PeakResponse> getAnnotatedPeaks() {
        return annotatedPeaks;
    }

    public void setAnnotatedPeaks(List<PeakResponse> annotatedPeaks) {
        this.annotatedPeaks = annotatedPeaks;
    }

    public List<PeakResponse> getAnnotatedPeaksWithRejectedAnnotations() {
        return annotatedPeaksWithRejectedAnnotations;
    }

    public void setAnnotatedPeaksWithRejectedAnnotations(List<PeakResponse> annotatedPeaksWithRejectedAnnotations) {
        this.annotatedPeaksWithRejectedAnnotations = annotatedPeaksWithRejectedAnnotations;
    }

    public List<PeakConflict> getConflicts() {
        return conflicts;
    }

    public void setConflicts(List<PeakConflict> conflicts) {
        this.conflicts = conflicts;
    }
}

