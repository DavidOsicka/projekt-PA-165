/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pa165.ddtroops;

import com.pa165.ddtroops.dao.RoleDAO;
import com.pa165.ddtroops.daoimpl.RoleDAOImpl;
import com.pa165.ddtroops.entity.Role;
import java.util.List;
import java.util.Random;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * This class is testing DAO implementation for role entity.
 * @author Jakub Kovařík
 */
public class RoleTest {
    /*private final RoleDAO roleDAO;
    
    public RoleTest(){
        this.roleDAO = new RoleDAOImpl();
    }
    
    private Role createTestRole(){
        Role role = new Role();
        role.setName("Crazy wizard "+ new Random().nextInt());
        role.setDescription("Description of the role");
        role.setDefense(100);
        role.setAttack(20);
        role.setEnergy(100);
        
        return role;
    }
    
    
    private Role createSingleRole(){
        Role role = createTestRole();
        roleDAO.createRole(role);
        return role;
    }
    
    @Test
    public void createRoleTest(){
        Role role = createSingleRole();
        Assert.assertEquals(roleDAO.retrieveRoleById(role.getId()),role,"Expected role is not equal");
    }
    
    @Test
    public void updateRoleTest(){
        Role role = createSingleRole();
        role.setDescription("This is new description.");
        role.setAttack(40);
        role.setDefense(50);
        role.setEnergy(80);
        roleDAO.updateRole(role);
        Role dbRole = roleDAO.retrieveRoleById(role.getId());
        Assert.assertEquals(dbRole.getDescription(),"This is new description.","Description is not equal.");
        Assert.assertEquals(dbRole.getAttack(),40,"Attack is not equal.");
        Assert.assertEquals(dbRole.getDefense(),50,"Defense is not equal.");
        Assert.assertEquals(dbRole.getEnergy(),80,"Energy is not equal.");
    }
    
    @Test
    public void deleteRoleTest(){
        Role role = createSingleRole();
        roleDAO.deleteRole(role);
        Role dbRole = roleDAO.retrieveRoleById(role.getId());
        Assert.assertNull(dbRole, "Role was not deleted.");
    }
    
    @Test
    public void retrieveAllRolesTest(){
        List<Role> allRoles = roleDAO.retrieveAllRoles();
        if(allRoles.size() > 0){
            for(Role r : allRoles){
                roleDAO.deleteRole(r);
            }
        }
        allRoles = roleDAO.retrieveAllRoles();
        Assert.assertEquals(allRoles.size(),0);
        
        for(int  i = 0; i < 5; i++){
            createSingleRole();
        }
        allRoles = roleDAO.retrieveAllRoles();
        Assert.assertEquals(allRoles.size(),5);
    }
    
    @Test
    public void retrieveRoleByIdTest(){
        Role role = createSingleRole();
        Role dbRole = roleDAO.retrieveRoleById(role.getId());
        Assert.assertNotNull(dbRole);
    }
    
    @Test
    public void retrieveRoleByNameTest(){
        Role role = createTestRole();
        role.setName("Alfamale");
        roleDAO.createRole(role);
        Role dbRole = roleDAO.retrieveRoleByName("Alfamale");
        Assert.assertNotNull(dbRole);
    }*/
    
    
}
