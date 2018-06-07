package com.polimi.awt.payload;

import com.polimi.awt.model.Peak;

import java.util.ArrayList;
import java.util.List;

public class PeakResponseBuilder {

    public PeakResponse buildOne (Peak peak) {
        return new PeakResponse(peak.getId(),peak.getName());
    }

    public List<PeakResponse> buildList (List<Peak> peakList) {

        List<PeakResponse> responses = new ArrayList<>();
        for (Peak peak:peakList
             ) {

            responses.add(this.buildOne(peak));
        }
        return responses;
    }
}
