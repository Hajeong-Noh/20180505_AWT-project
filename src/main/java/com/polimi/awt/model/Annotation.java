package com.polimi.awt.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity()
public class Annotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date creationDate;

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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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
