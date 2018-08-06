package com.polimi.awt.payload.utils;

import com.polimi.awt.model.CampaignStatistics;
import com.polimi.awt.model.PeakConflict;
import com.polimi.awt.payload.PeakResponse;
import com.polimi.awt.repository.AnnotationRepository;
import com.polimi.awt.repository.PeakConflictRepository;
import com.polimi.awt.repository.PeakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignStatisticsBuilder {

    @Autowired
    private PeakRepository peakRepository;

    @Autowired
    private PeakConflictRepository peakConflictRepository;

    @Autowired
    private AnnotationRepository annotationRepository;

    public CampaignStatistics buildForCampaign(Long campaignId) {

        List<PeakResponse> annotatedPeaks = new PeakResponseBuilder().buildList(peakRepository.findAnnotatedPeaksByCampaignId(campaignId));
        List<PeakResponse> annotatedPeaksWithRejectedAnnotations = new PeakResponseBuilder().buildList(peakRepository.findAnnotatedPeaksWithRejectedAnnotationByCampaignId(campaignId));
        List<PeakConflict> conflicts = peakConflictRepository.findPeakConflictsByCampaignId(campaignId);


        int totalNumberOfPeaksToBeAnnotated = peakRepository.countPeaksByCampaignIdAndToBeAnnotated(campaignId, true);

        int numberOfAnnotatedPeaks = annotationRepository.countPeaksInAnnotatedState(campaignId);
        int numberOfStartedPeaks = totalNumberOfPeaksToBeAnnotated - numberOfAnnotatedPeaks;
        int numberOfPeaksWithRejectedAnnotations = peakRepository.countAnnotatedPeaksWithRejectedAnnotationByCampaignId(campaignId);
        int numberOfConflicts = annotationRepository.countNumberOfConflictsByCampaignId(campaignId);

        return new CampaignStatistics(numberOfStartedPeaks, numberOfAnnotatedPeaks,
                numberOfPeaksWithRejectedAnnotations, numberOfConflicts, annotatedPeaks,
                annotatedPeaksWithRejectedAnnotations, conflicts);
    }
}
