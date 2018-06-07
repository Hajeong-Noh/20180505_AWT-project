package com.polimi.awt.controller;

import com.polimi.awt.exception.PreconditionFailedException;
import com.polimi.awt.exception.UnauthorizedException;
import com.polimi.awt.model.Annotation;
import com.polimi.awt.model.Campaign;
import com.polimi.awt.model.Peak;
import com.polimi.awt.model.users.Manager;
import com.polimi.awt.model.users.Worker;
import com.polimi.awt.payload.AnnotationRequest;
import com.polimi.awt.payload.AnnotationResponse;
import com.polimi.awt.payload.httpResponseStatus.ApiResponse;
import com.polimi.awt.payload.httpResponseStatus.CreatedResponse;
import com.polimi.awt.payload.httpResponseStatus.OkResponse;
import com.polimi.awt.payload.services.AnnotationResponseBuilder;
import com.polimi.awt.repository.AnnotationRepository;
import com.polimi.awt.repository.CampaignRepository;
import com.polimi.awt.repository.PeakRepository;
import com.polimi.awt.repository.UserRepository;
import com.polimi.awt.security.CurrentUser;
import com.polimi.awt.security.UserPrincipal;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(description="Operations related to Annotations", tags = "Annotations")
public class AnnotationController {

    @Autowired
    private AnnotationRepository annotationRepository;
    @Autowired
    private PeakRepository peakRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CampaignRepository campaignRepository;

    @GetMapping("/campaigns/{campaignId}/peaks/{peakId}/annotations")
    public List<AnnotationResponse> getAnnotationsForPeak(@PathVariable Long peakId) {

        List<Annotation> annotations = annotationRepository.findAnnotationsByPeakId(peakId);
        return new AnnotationResponseBuilder().buildList(annotations);
    }

    @GetMapping("/campaigns/{campaignId}/peaks/{peakId}/annotations/{annotationId}")
    public AnnotationResponse findAnnotationById(@PathVariable Long annotationId) {
        Annotation annotation = annotationRepository.findAnnotationById(annotationId);
        return new AnnotationResponseBuilder().buildOne(annotation);
    }

    @PostMapping("/campaigns/{campaignId}/peaks/{peakId}/annotations")
    @PreAuthorize("hasAuthority('WORKER')")
    public ApiResponse createAnnotation (@CurrentUser UserPrincipal currentUser,
                                         @PathVariable Long peakId, @PathVariable Long campaignId,
                                         @RequestBody AnnotationRequest request){

        Worker worker = (Worker) userRepository.findUserById(currentUser.getId());
        Campaign campaign = campaignRepository.findCampaignById(campaignId);

        if (!worker.getEnrolledCampaigns().contains(campaign)) {
            throw new UnauthorizedException("You are not enrolled in this campaign");
        }

        Peak peak = peakRepository.findPeakById(peakId);

        if (!peak.isToBeAnnotated()) {
            throw new PreconditionFailedException("This peak cannot be annotated");
        }

        if (annotationRepository.existsAnnotationByPeakAndWorkerId(peak, worker.getId())) {
            throw new PreconditionFailedException("You can add maximum one annotation for a peak.");
        }

        annotationRepository.save(
                worker.createAnnotation(
                        peak,
                        request.getValid(),
                        request.getElevation(),
                        request.getName(),
                        request.getLocalizedPeakNames(),
                        currentUser.getId()
                )
        );
        return new CreatedResponse();
    }

    @PatchMapping("/campaigns/{campaignId}/peaks/{peakId}/annotations/{annotationId}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public ApiResponse updateAnnotationStatus(@CurrentUser UserPrincipal currentUser, @PathVariable Long annotationId,
                                                 @RequestParam(value = "accepted") boolean isAccepted) {

        Manager manager = (Manager) userRepository.findUserById(currentUser.getId());
        Annotation annotation = annotationRepository.findAnnotationById(annotationId);

        if (!annotation.getPeak().getCampaign().getManager().getId().
                equals(currentUser.getId())) {

            throw new UnauthorizedException();
        }

        annotationRepository.save(manager.updateAnnotationStatus(annotation, isAccepted));
        return new OkResponse();
    }
}
