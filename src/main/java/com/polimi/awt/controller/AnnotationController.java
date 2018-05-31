package com.polimi.awt.controller;

import com.polimi.awt.model.Annotation;
import com.polimi.awt.model.users.Manager;
import com.polimi.awt.model.users.Worker;
import com.polimi.awt.payload.AnnotationRequest;
import com.polimi.awt.repository.AnnotationRepository;
import com.polimi.awt.repository.UserRepository;
import com.polimi.awt.security.CurrentUser;
import com.polimi.awt.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public Set<Annotation> getAnnotationsForPeak(@PathVariable Long peakId) {
        return annotationRepository.findAllByPeakId(peakId);
    }

    @GetMapping("/campaigns/{campaignId}/peaks/{peakId}/annotations/{annotationId}")
    public Annotation findAnnotationById(@PathVariable Long annotationId) {
        return annotationRepository.findAnnotationById(annotationId);
    }

    @PostMapping("/campaigns/{campaignId}/peaks/{peakId}/annotations")
    @PreAuthorize("hasAuthority('WORKER')")
    public Annotation createAnnotation(@RequestBody AnnotationRequest request) {
        Worker worker = (Worker) userRepository.findUserById(request.getWorkerId());
        return annotationRepository.save(worker.createAnnotation(request.getValid(), request.getElevation(),
                request.getName(), request.getLocalizedPeakNames()));
    }

    @PatchMapping("/campaigns/{campaignId}/peaks/{peakId}/annotations/{annotationId}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ResponseEntity updateAnnotationStatus(@CurrentUser UserPrincipal currentUser, @PathVariable Long annotationId,
                                                 @RequestBody AnnotationRequest request) {
        Manager manager = (Manager) userRepository.findUserById(currentUser.getId());
        Annotation annotation = annotationRepository.findAnnotationById(annotationId);
        if (annotation.getPeak().getCampaign().getManager().getId().
                equals(currentUser.getId())) {

            manager.updateAnnotationStatus(annotation, request.isAcceptedByManager());
            annotationRepository.save(manager.updateAnnotationStatus(annotation, request.isAcceptedByManager()));
            return ResponseEntity.status(HttpStatus.OK).body("Successful");
        }
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).
                    body("You are not authorized to accept or reject this annotation.");
    }
}
