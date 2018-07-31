package com.polimi.awt.payload;


import com.polimi.awt.model.LocalizedPeakName;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AnnotationResponse {


    private Long id;
    private boolean isValid;
    private double elevation;
    private String name;

    private List<LocalizedPeakName> localizedPeakNames = new ArrayList<>();

    private Boolean isAcceptedByManager;

    private Long peakId;

    private String workerName;

    private LocalDateTime creationDateTime;

    public AnnotationResponse(Long id, boolean isValid, double elevation, String name, List<LocalizedPeakName> localizedPeakNames, Boolean isAcceptedByManager, Long peakId, String workerName, LocalDateTime creationDateTime) {
        this.id = id;
        this.isValid = isValid;
        this.elevation = elevation;
        this.name = name;
        this.localizedPeakNames = localizedPeakNames;
        this.isAcceptedByManager = isAcceptedByManager;
        this.peakId = peakId;
        this.workerName = workerName;
        this.creationDateTime = creationDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<LocalizedPeakName> getLocalizedPeakNames() {
        return localizedPeakNames;
    }

    public void setLocalizedPeakNames(List<LocalizedPeakName> localizedPeakNames) {
        this.localizedPeakNames = localizedPeakNames;
    }

    public Boolean getAcceptedByManager() {
        return isAcceptedByManager;
    }

    public void setAcceptedByManager(Boolean acceptedByManager) {
        isAcceptedByManager = acceptedByManager;
    }

    public Long getPeakId() {
        return peakId;
    }

    public void setPeakId(Long peakId) {
        this.peakId = peakId;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }
}
