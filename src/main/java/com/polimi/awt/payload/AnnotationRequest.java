package com.polimi.awt.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.polimi.awt.model.LocalizedPeakName;

import java.util.ArrayList;
import java.util.List;

public class AnnotationRequest {

    private boolean isValid;

    private Double elevation;

    private String name;

    @JsonProperty(value = "localized_names")
    private List<LocalizedPeakName> localizedPeakNames = new ArrayList<>();

    private boolean isAcceptedByManager;

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
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

    public void setLocalizedPeakNames(ArrayList<LocalizedPeakName> localizedPeakNames) {
        this.localizedPeakNames = localizedPeakNames;
    }

    public boolean isAcceptedByManager() {
        return isAcceptedByManager;
    }

    public void setAcceptedByManager(boolean acceptedByManager) {
        isAcceptedByManager = acceptedByManager;
    }
}
