package com.polimi.awt.repository;

import com.polimi.awt.model.Campaign;
import com.polimi.awt.model.CampaignStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    Campaign findCampaignById(Long id);
    List<Campaign> findCampaignByCampaignStatus(CampaignStatus campaignStatus);
    List <Campaign> findAllByManager_Id(Long managerId);
}
