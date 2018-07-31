package com.polimi.awt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.polimi.awt.model.users.User;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties("peak")
public class Annotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, updatable = false)
    private LocalDateTime creationDateTime;

    private boolean isValid;
    private Double elevation;
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "annotation_id", referencedColumnName = "id")
    @JsonProperty(value = "localized_names")
    private List<LocalizedPeakName> localizedPeakNames = new ArrayList<>();

    @Column(nullable = true)
    private Boolean isAcceptedByManager;

    @ManyToOne
    private Peak peak;

    @OneToOne
    private User worker;

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

    public Double getElevation() {
        return elevation;
    }

    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LocalizedPeakName> getLocalizedPeakNames() {
        return localizedPeakNames;
    }

    public void setLocalizedPeakNames(List<LocalizedPeakName> localizedPeakNames) {
        this.localizedPeakNames = localizedPeakNames;
    }

    public Boolean isAcceptedByManager() {
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

    public User getWorker() {
        return worker;
    }

    public void setWorker(User worker) {
        this.worker = worker;
    }

    public Boolean getAcceptedByManager() {
        return isAcceptedByManager;
    }

    public void setAcceptedByManager(Boolean acceptedByManager) {
        isAcceptedByManager = acceptedByManager;
    }
}