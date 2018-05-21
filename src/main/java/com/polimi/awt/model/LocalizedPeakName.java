package com.polimi.awt.model;

import javax.persistence.*;

@Entity
public class LocalizedPeakName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String language;
    private String localizedName;
    @ManyToOne
    private Peak peak;
    @ManyToOne
    private Annotation annotation;
}
