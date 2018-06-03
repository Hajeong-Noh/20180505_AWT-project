package com.polimi.awt.repository;

import com.polimi.awt.model.Peak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PeakRepository extends JpaRepository<Peak, Long> {
    Set <Peak> findAllByCampaignId(Long campaignId);

    Peak findPeakById (Long peakId);

    int countPeaksByCampaignId(Long campaignId);
}
