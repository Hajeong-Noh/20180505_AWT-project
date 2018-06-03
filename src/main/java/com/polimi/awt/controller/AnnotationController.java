package com.polimi.awt.controller;

import com.polimi.awt.exception.PreconditionFailedException;
import com.polimi.awt.exception.UnauthorizedException;
import com.polimi.awt.model.Annotation;
import com.polimi.awt.model.Peak;
import com.polimi.awt.model.users.Manager;
import com.polimi.awt.model.users.Worker;
import com.polimi.awt.payload.AnnotationRequest;
import com.polimi.awt.payload.HttpResponseStatus.ApiResponse;
import com.polimi.awt.payload.HttpResponseStatus.CreatedResponse;
import com.polimi.awt.payload.HttpResponseStatus.OkResponse;
import com.polimi.awt.repository.AnnotationRepository;
import com.polimi.awt.repository.PeakRepository;
import com.polimi.awt.repository.UserRepository;
import com.polimi.awt.security.CurrentUser;
import com.polimi.awt.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class AnnotationController {

    @Autowired
    private AnnotationRepository annotationRepository;
    @Autowired
    private PeakRepository peakRepository;
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
    @PreAuthorize("hasAuthority('WORKER')")
    public ApiResponse createAnnotation (@CurrentUser UserPrincipal currentUser,
                                         @PathVariable Long peakId, @RequestBody AnnotationRequest request){

        Worker worker = (Worker) userRepository.findUserById(currentUser.getId());
        Peak peak = peakRepository.findPeakById(peakId);

        if (annotationRepository.findAnnotationByPeakAndWorkerId(peak, currentUser.getId()) == null) {
            annotationRepository.save(worker.createAnnotation(peak, request.getValid(), request.getElevation(),
                    request.getName(), request.getLocalizedPeakNames(), currentUser.getId()));
            return new CreatedResponse();
        }
        else throw new PreconditionFailedException("You can add maximum one annotation for a peak.");
    }

    @PatchMapping("/campaigns/{campaignId}/peaks/{peakId}/annotations/{annotationId}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ApiResponse updateAnnotationStatus(@CurrentUser UserPrincipal currentUser, @PathVariable Long annotationId,
                                                 @RequestBody AnnotationRequest request) {

        Manager manager = (Manager) userRepository.findUserById(currentUser.getId());
        Annotation annotation = annotationRepository.findAnnotationById(annotationId);

        if (!annotation.getPeak().getCampaign().getManager().getId().
                equals(currentUser.getId())) {

            throw new UnauthorizedException();
        }

        manager.updateAnnotationStatus(annotation, request.isAcceptedByManager());
        annotationRepository.save(manager.updateAnnotationStatus(annotation, request.isAcceptedByManager()));
        return new OkResponse();
    }
}
