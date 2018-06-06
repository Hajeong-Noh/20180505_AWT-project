package com.polimi.awt.repository;

import com.polimi.awt.model.Campaign;
import com.polimi.awt.model.CampaignStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    Campaign findCampaignById(Long id);
    List<Campaign> findCampaignByCampaignStatus(CampaignStatus campaignStatus);
    List <Campaign> findAllByManager_Id(Long managerId);

    List<Campaign> findAllByCampaignStatus(CampaignStatus status);

    @Query(value = "SELECT * FROM campaign c JOIN user_enrolled_campaigns uec " +
            "WHERE c.id = uec.enrolled_campaigns_id AND uec.worker_id = :workerId", nativeQuery = true)
    List<Campaign> findEnrolledCampaigns(@Param("workerId") Long workerId);

    @Query(value = "SELECT * " +
            "FROM campaign c LEFT JOIN user_enrolled_campaigns uec ON c.id = uec.enrolled_campaigns_id " +
            "WHERE campaign_status = 'STARTED' AND (worker_id IS NULL OR NOT worker_id = :workerId)",
            nativeQuery = true)
    List<Campaign> findNotEnrolledCampaigns(@Param("workerId") Long workerId);
}
