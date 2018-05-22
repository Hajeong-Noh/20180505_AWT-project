package com.polimi.awt.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Peak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double latitude;
    private double longitude;
    private double elevation;
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "peak_id", referencedColumnName = "id")
    private Set<LocalizedPeakName> localizedPeakNames = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "peak_id", referencedColumnName = "id")
    private Set <Annotation> annotations = new HashSet<>();
    private boolean toBeAnnotated;
    private String dataProvenance;
}
