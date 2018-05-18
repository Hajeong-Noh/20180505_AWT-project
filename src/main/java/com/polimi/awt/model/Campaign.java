package com.polimi.awt.model;

import java.time.LocalDateTime;

@Entity
public class Campaign {

    private Long id;
    private String name;
    private CampaignStatus campaignStatus;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
