package com.polimi.awt.controller;

import com.polimi.awt.model.Annotation;
import com.polimi.awt.model.users.Manager;
import com.polimi.awt.model.users.Worker;
import com.polimi.awt.payload.AnnotationRequest;
import com.polimi.awt.repository.AnnotationRepository;
import com.polimi.awt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class AnnotationController {
    @Autowired
    private AnnotationRepository annotationRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/campaigns/{campaignId}/peaks/{peakId}/annotations")
    private Set<Annotation> getAnnotationsForPeak (@PathVariable Long peakId) {
        return annotationRepository.findAllByPeakId(peakId);
    }

    @GetMapping("/campaigns/{campaignId}/peaks/{peakId}/annotations/{annotationId}")
    private Annotation findAnnotationById (@PathVariable Long annotationId) {
        return annotationRepository.findAnnotationById(annotationId);
    }

    @PostMapping("/campaigns/{campaignId}/peaks/{peakId}/annotations")
    private Annotation createAnnotation (@RequestBody AnnotationRequest request){
        Worker worker = (Worker) userRepository.findUserById(request.getWorkerId());
        return annotationRepository.save(worker.createAnnotation(request.getValid(), request.getElevation(),
                request.getName(), request.getLocalizedPeakNames()));
    }

    @PatchMapping("/campaigns/{campaignId}/peaks/{peakId}/annotations/{annotationId}")
    private Annotation updateAnnotationStatus (@PathVariable Long annotationId, @RequestBody AnnotationRequest request) {
        Manager manager = (Manager) userRepository.findUserById(request.getManagerId());
        Annotation annotation = annotationRepository.findAnnotationById(annotationId);
        return annotationRepository.save(manager.udpateAnnotationStatus(annotation, request.isAcceptedByManager()));
    }
}
