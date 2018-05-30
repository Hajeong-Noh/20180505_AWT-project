package com.polimi.awt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@JsonIgnoreProperties("peak")
public class Annotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, updatable = false)
    private LocalDateTime creationDateTime;

    private boolean isValid;
    private double elevation;
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "annotation_id", referencedColumnName = "id")
    private Set<LocalizedPeakName> localizedPeakNames;

    private boolean isAcceptedByManager;

    @ManyToOne
    private Peak peak;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<LocalizedPeakName> getLocalizedPeakNames() {
        return localizedPeakNames;
    }

    public void setLocalizedPeakNames(Set<LocalizedPeakName> localizedPeakNames) {
        this.localizedPeakNames = localizedPeakNames;
    }

    public boolean isAcceptedByManager() {
        return isAcceptedByManager;
    }

    public void setAcceptedByManager(boolean acceptedByManager) {
        isAcceptedByManager = acceptedByManager;
    }

    public Peak getPeak() {
        return peak;
    }

    public void setPeak(Peak peak) {
        this.peak = peak;
    }

}
