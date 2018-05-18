package com.polimi.awt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Annotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // private Peak peak;
    private Date creationDate;
    private boolean isValid;
    private double elevation;
    private String name;
    // private List <String> localizedPeakNames;
    private boolean isAcceptedByManager;

}
