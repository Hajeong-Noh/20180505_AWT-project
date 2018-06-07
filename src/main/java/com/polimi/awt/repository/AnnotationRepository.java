package com.polimi.awt.repository;

import com.polimi.awt.model.Annotation;
import com.polimi.awt.model.Peak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnotationRepository extends JpaRepository<Annotation, Long> {

    @Query(value = "SELECT a.id, username, creation_date_time, name, elevation, is_valid, is_accepted_by_manager " +
            "FROM annotation a JOIN user u ON  worker_id = u.id WHERE peak_id = :peakId", nativeQuery = true)
    List<Annotation> findAllByPeakId(@Param("peakId") Long peakId);


    Annotation findAnnotationById(Long annotationId);

    List<Annotation> findAnnotationsByPeakId(Long peakId);

    Annotation findAnnotationByPeakAndWorkerId(Peak peak, Long workerId);

    boolean existsAnnotationByPeakAndWorkerId(Peak peak, Long workerId);

    @Query(value = "SELECT * FROM annotation an LEFT JOIN localized_peak_name lpn on an.id = lpn.annotation_id WHERE an.peak_id IN " +
            "(SELECT peak.id FROM peak WHERE campaign_id = :campaignId AND peak.to_be_annotated = 1)"
            , nativeQuery = true)
    List<Annotation> findAllAnnotationsByCampaignId(@Param("campaignId") Long campaignId);

    @Query(value = "SELECT * FROM annotation an LEFT JOIN localized_peak_name lpn on an.id = lpn.annotation_id WHERE an.peak_id IN " +
            "(SELECT peak.id FROM peak WHERE campaign_id = :campaignId AND peak.to_be_annotated = 1 AND an.is_accepted_by_manager = 0)"
            , nativeQuery = true)
    List<Annotation> findRejectedAnnotationsByCampaignId(@Param("campaignId") Long campaignId);

    @Query(value = "SELECT count(distinct an.peak_id) FROM annotation an WHERE an.peak_id IN (SELECT peak.id FROM peak " +
            "WHERE campaign_id = :campaignId AND peak.to_be_annotated = 1)"
            , nativeQuery = true)
    int countPeaksInAnnotatedState(@Param("campaignId") Long campaignId);

    @Query(value = "SELECT count(distinct an.peak_id) FROM annotation an WHERE an.peak_id IN " +
            "(SELECT peak.id FROM peak WHERE campaign_id = :campaignId AND peak.to_be_annotated = 1) AND an.is_accepted_by_manager = 0"
            , nativeQuery = true)
    int countPeaksWithRejectedAnnotations(@Param("campaignId") Long campaignId);

    @Query(value = "select count(*) as conflicts from " +
            "(select peak_id from peak p join annotation a on p.id = a.peak_id where p.campaign_id = :campaignId group by peak_id having count(*) > 1 and min(is_valid) = 0 and max(is_valid) =1) conflictPeaks",
            nativeQuery = true)
    int countNumberOfConflictsByCampaignId(@Param("campaignId") Long campaignId);


}

