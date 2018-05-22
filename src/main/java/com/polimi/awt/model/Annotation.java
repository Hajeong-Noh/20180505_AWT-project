package com.polimi.awt.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Annotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date creationDate;
    private boolean isValid;
    private double elevation;
    private String name;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "annotation_id", referencedColumnName = "id")
    private Set<LocalizedPeakName> localizedPeakNames;
    private boolean isAcceptedByManager;

}
