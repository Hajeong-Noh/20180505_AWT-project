package com.polimi.awt.repository;

import com.polimi.awt.model.Annotation;
import com.polimi.awt.model.Peak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AnnotationRepository extends JpaRepository<Annotation, Long> {
    Set<Annotation> findAllByPeakId(Long peakId);

    Annotation findAnnotationById (Long annotationId);

    Annotation findAnnotationByPeakAndWorkerId(Peak peak, Long workerId);
}
