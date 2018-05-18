package com.polimi.awt.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserControllerTest {

    private UserController userController;

    @Before
    public void setUp() throws Exception {

        userController = new UserController();
    }

    @Test
    public void testInstantiation() {

        Assert.assertNotNull(userController);
    }

    //TODO: Add Test with mockdatabase to load roles
}
