package com.polimi.awt.payload;

import com.polimi.awt.model.RoleName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SignUpRequestTest {

    private SignUpRequest managerSignUp;
    private SignUpRequest workerSignUp;


    @Before
    public void setUp() {
        managerSignUp = new SignUpRequest();
        managerSignUp.setEmail("manager@test.com");
        managerSignUp.setPassword("pa55w0rd_m");
        managerSignUp.setRole("MANAGER");

        workerSignUp = new SignUpRequest();
        workerSignUp.setEmail("worker@test.com");
        workerSignUp.setPassword("pa55w0rd_w");
        workerSignUp.setRole("WORKER");
    }

    @Test
    public void testInstantiation() {


        Assert.assertNotNull(managerSignUp);
        Assert.assertEquals("manager@test.com", managerSignUp.getEmail());
        Assert.assertEquals("pa55w0rd_m", managerSignUp.getPassword());
        Assert.assertEquals("MANAGER", managerSignUp.getRole());
    }

    @Test
    public void roleToRoleName_shouldReturnManager() {
        Assert.assertEquals(managerSignUp.roleToRoleName(), RoleName.MANAGER);
    }
    @Test
    public void roleToRoleName_shouldReturnWorker() {
        Assert.assertEquals(workerSignUp.roleToRoleName(), RoleName.WORKER);
    }
}
