package com.polimi.awt.repository;

import com.polimi.awt.model.Peak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PeakRepository extends JpaRepository<Peak, Long> {
    Set <Peak> findAllByCampaignId(Long campaignId);

    Peak findPeakById (Long peakId);

    int countPeaksByCampaignIdAndToBeAnnotated(Long campaignId, boolean toBeAnnotated);

    @Query (value = "select distinct(p.id), p.elevation, p.longitude, p.latitude, p.name, p.provenance, p.to_be_annotated, p.campaign_id   from " +
            "peak p join annotation a on p.id = a.peak_id where p.campaign_id = :campaignId",
            nativeQuery = true)
    List<Peak> findAnnotatedPeaksByCampaignId(@Param(value = "campaignId") Long campaignId);

    @Query (value = "select distinct(p.id), p.elevation, p.longitude, p.latitude, p.name, p.provenance, p.to_be_annotated, p.campaign_id   from " +
            "peak p join annotation a on p.id = a.peak_id where p.campaign_id = :campaignId and a.is_accepted_by_manager = 0",
            nativeQuery = true)
    List<Peak> findAnnotatedPeaksWithRejectedAnnotationByCampaignId(@Param(value = "campaignId") Long campaignId);

    @Query(value = "select count(distinct(peak_id)) count from " +
            "peak p join annotation a on p.id = a.peak_id " +
            "where p.campaign_id = :campaignId and a.is_accepted_by_manager = 0",
            nativeQuery = true)
    int countAnnotatedPeaksWithRejectedAnnotationByCampaignId(@Param(value = "campaignId") Long campaignId);

}
