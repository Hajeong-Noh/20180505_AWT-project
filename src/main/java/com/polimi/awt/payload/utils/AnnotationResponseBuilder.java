package com.polimi.awt.payload.utils;

import com.polimi.awt.model.Annotation;
import com.polimi.awt.payload.AnnotationResponse;

import java.util.ArrayList;
import java.util.List;

public class AnnotationResponseBuilder {

    public AnnotationResponseBuilder() {
    }

    public AnnotationResponse buildOne(Annotation annotation) {
        return new AnnotationResponse(

                annotation.getId(),
                annotation.isValid(),
                annotation.getElevation(),
                annotation.getName(),
                annotation.getLocalizedPeakNames(),
                annotation.isAcceptedByManager(),
                annotation.getPeak().getId(),
                annotation.getWorker().getUsername(),
                annotation.getCreationDateTime()
        );
    }

    public List<AnnotationResponse> buildList (List<Annotation> annotations) {

        List<AnnotationResponse> responses = new ArrayList<>();

        for (Annotation annotation: annotations
             ) {
            responses.add(this.buildOne(annotation));
        }
        return responses;
    }
}
