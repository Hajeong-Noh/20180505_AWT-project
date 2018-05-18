package com.polimi.awt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Peak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double latitude;
    private double longitude;
    private double elevation;
    private String name;
    // private List <String> localizedPeakNames;
    private boolean toBeAnnotated;
    private String dataProvenance;
}
