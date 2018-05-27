package com.polimi.awt.model;

import com.polimi.awt.model.users.Manager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PeakTest {

    private Manager manager;
    private Peak peak;


    @Before
    public void setUp() {
        manager = new Manager();
        peak = new Peak();
        peak.setCampaign(manager.createCampaign("Alps"));
    }

    @Test
    public void inverseToBeAnnotatedTest_shouldChangeToFalse() {
        peak.setToBeAnnotated(true);

        peak.inverseToBeAnnotated();
        Assert.assertFalse(peak.isToBeAnnotated());
    }
    @Test
    public void inverseToBeAnnotatedTest_shouldChangeToTrue() {
        peak.setToBeAnnotated(false);

        peak.inverseToBeAnnotated();
        Assert.assertTrue(peak.isToBeAnnotated());
    }
    @Test(expected = RuntimeException.class)
    public void inverseToBeAnnotatedTestWithStartedCampaign_shouldThrowException() {
        peak.setToBeAnnotated(true);
        peak.getCampaign().setCampaignStatus(CampaignStatus.STARTED);

        peak.inverseToBeAnnotated();
    }
    @Test(expected = RuntimeException.class)
    public void inverseToBeAnnotatedTestWithClosedCampaign_shouldThrowException() {
        peak.setToBeAnnotated(true);
        peak.getCampaign().setCampaignStatus(CampaignStatus.CLOSED);

        peak.inverseToBeAnnotated();
    }


}
