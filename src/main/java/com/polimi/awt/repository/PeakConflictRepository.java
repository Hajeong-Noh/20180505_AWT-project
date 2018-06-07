package com.polimi.awt.repository;

import com.polimi.awt.model.PeakConflict;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PeakConflictRepository {

    @PersistenceContext
    private EntityManager entitiyManager;

    public List<PeakConflict> findPeakConflictsByCampaignId(Long campaignId) {

        List<Object[]> resultListHelper = entitiyManager.createNativeQuery("select p.id peakId, p.name peakName, " +
                "count(is_valid) numberOfAnnotationsWithValidPeak, count(!is_valid) numberOfAnnotationsWithInvalidPeak " +
                "from peak p join annotation a on p.id = a.peak_id " +
                "where p.campaign_id = :campaignId " +
                "group by peak_id " +
                "having count(*) > 1 and min(is_valid) = 0 and max(is_valid) = 1 ##List of Peaks with conflicts").setParameter("campaignId", campaignId).getResultList();

        List<PeakConflict> resultList = new ArrayList<>();
        for (Object[] currentObject : resultListHelper
                ) {
            BigInteger peakId = (BigInteger) currentObject[0];
            String peakName = (String) currentObject[1];
            BigInteger numberOfAnnotationsWithValidPeak = (BigInteger) currentObject[2];
            BigInteger numberOfAnnotationsWithInvalidPeak = (BigInteger) currentObject[3];

            resultList.add(
                    new PeakConflict(
                            peakId.longValue(),
                            peakName,
                            numberOfAnnotationsWithValidPeak.intValue(),
                            numberOfAnnotationsWithInvalidPeak.intValue()
                    )
            );
        }
        return resultList;
    }

}
