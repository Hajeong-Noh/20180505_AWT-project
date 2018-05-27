package com.polimi.awt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@JsonIgnoreProperties("campaign")
public class Peak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double latitude;
    private double longitude;
    private double elevation;
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "peak_id", referencedColumnName = "id")
    @JsonProperty(value = "localized_names")
    private List<LocalizedPeakName> localizedNames = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "peak_id", referencedColumnName = "id")
    private Set <Annotation> annotations = new HashSet<>();
    private boolean toBeAnnotated;
    private String provenance;

    @ManyToOne
    private Campaign campaign;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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

    public List<LocalizedPeakName> getLocalizedNames() {
        return localizedNames;
    }

    public void setLocalizedNames(List<LocalizedPeakName> localizedNames) {
        this.localizedNames = localizedNames;
    }

    public Set<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(Set<Annotation> annotations) {
        this.annotations = annotations;
    }

    public boolean isToBeAnnotated() {
        return toBeAnnotated;
    }

    public void setToBeAnnotated(boolean toBeAnnotated) {
        this.toBeAnnotated = toBeAnnotated;
    }

    public void inverseToBeAnnotated () {
        //TODO: add exception
        if (this.campaign.getCampaignStatus() != CampaignStatus.CREATED) {
            throw new RuntimeException("The property can only be changed if the campaign is in the CREATED status.");
        }
        this.toBeAnnotated = !this.toBeAnnotated;
    }

    public String getProvenance() {
        return provenance;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }
}
