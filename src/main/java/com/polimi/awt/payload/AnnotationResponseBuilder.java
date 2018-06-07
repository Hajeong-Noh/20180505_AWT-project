package com.polimi.awt.payload;

import com.polimi.awt.model.Annotation;

import java.util.ArrayList;
import java.util.List;

public class AnnotationResponseBuilder {

    public AnnotationResponseBuilder() {
    }

    public AnnotationResponse build (Annotation annotation) {
        return new AnnotationResponse(

                annotation.getId(),
                annotation.isValid(),
                annotation.getElevation(),
                annotation.getName(),
                annotation.getLocalizedPeakNames(),
                annotation.isAcceptedByManager(),
                annotation.getPeak().getId(),
                annotation.getWorker().getUsername());
    }

    public List<AnnotationResponse> buildList (List<Annotation> annotations) {

        List<AnnotationResponse> responses = new ArrayList<>();

        for (Annotation annotation: annotations
             ) {
            responses.add(this.build(annotation));
        }
        return responses;
    }
}
