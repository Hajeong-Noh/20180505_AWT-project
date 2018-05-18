package com.polimi.awt.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private CampaignStatus campaignStatus;
    private Date startDate;
    private Date endDate;
}
