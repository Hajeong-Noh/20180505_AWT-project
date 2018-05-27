package com.polimi.awt.model.users;

import com.polimi.awt.model.Campaign;
import com.polimi.awt.model.CampaignStatus;
import com.polimi.awt.model.Role;
import com.polimi.awt.model.RoleName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ManagerTest {

    private Manager manager;
    Campaign campaign;

    @Before
    public void setUp() {
        manager = new Manager("Mr. Manager", "pa55w0rd", "mr@manager.com", new Role (RoleName.MANAGER));
        campaign = new Campaign();
    }

    @Test
    public void testInstantiation() {

        Assert.assertNotNull(manager);
        Assert.assertEquals("Mr. Manager", manager.getUsername());
        Assert.assertEquals("pa55w0rd", manager.getPassword());
        Assert.assertEquals("mr@manager.com", manager.getEmailAddress());
        Assert.assertFalse(manager.getRoles().isEmpty());
    }

    @Test
    public void createCampaignWithNameTest() {
        Campaign campaign = manager.createCampaign("Alps");

        Assert.assertEquals(CampaignStatus.CREATED, campaign.getCampaignStatus());
        Assert.assertEquals("Alps", campaign.getName());
        Assert.assertEquals(campaign.getManager(), manager);

    }

    @Test
    public void updateCreatedCampaignStatusTest_shouldChangeToStarted () {
        campaign.setCampaignStatus(CampaignStatus.CREATED);

        manager.updateCampaignStatus(campaign);
        Assert.assertEquals(CampaignStatus.STARTED, campaign.getCampaignStatus());
    }
    @Test
    public void updateStartedCampaignStatusTest_shouldChangeToClosed () {
        campaign.setCampaignStatus(CampaignStatus.STARTED);

        manager.updateCampaignStatus(campaign);
        Assert.assertEquals(campaign.getCampaignStatus(), CampaignStatus.CLOSED);
    }

    @Test(expected = RuntimeException.class)
    public void updateClosedCampaignStatusTest_shouldThrowException () {
        campaign.setCampaignStatus(CampaignStatus.CLOSED);

        manager.updateCampaignStatus(campaign);
    }

    @Test(expected = RuntimeException.class)
    public void updateCampaignStatusThreeTimes_shouldReturnException () {
        campaign.setCampaignStatus(CampaignStatus.CREATED);

        manager.updateCampaignStatus(campaign);
        manager.updateCampaignStatus(campaign);
        manager.updateCampaignStatus(campaign);
    }

    @Test
    public void updateCreatedCampaignStatusTwice_shouldChangeToClosed () {
        campaign.setCampaignStatus(CampaignStatus.CREATED);

        manager.updateCampaignStatus(campaign);
        manager.updateCampaignStatus(campaign);
        Assert.assertEquals(campaign.getCampaignStatus(), CampaignStatus.CLOSED);
    }
}
