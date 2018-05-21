package com.polimi.awt.model;

import com.polimi.awt.model.users.Manager;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    @OneToMany(mappedBy = "campaign", fetch = FetchType.EAGER)
    private Set <Peak> peakSet = new HashSet<>();
    @ManyToOne
    private Manager manager;
}
