package com.polimi.awt.model.users;

import com.polimi.awt.model.Role;
import com.polimi.awt.model.RoleName;
import org.junit.Assert;
import org.junit.Test;

public class ManagerTest {

    @Test
    public void testInstantiation() {
        User manager = new Manager("Mr. Manager", "pa55w0rd", "mr@manager.com", new Role (RoleName.MANAGER));
        manager.addRole(new Role(RoleName.MANAGER));

        Assert.assertNotNull(manager);
        Assert.assertEquals("Mr. Manager", manager.getUsername());
        Assert.assertEquals("pa55w0rd", manager.getPassword());
        Assert.assertEquals("mr@manager.com", manager.getEmailAddress());
        Assert.assertFalse(manager.getRoles().isEmpty());

    }
}
