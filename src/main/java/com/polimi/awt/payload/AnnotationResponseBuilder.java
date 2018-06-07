package com.polimi.awt.payload;

import com.polimi.awt.model.Annotation;

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
}
