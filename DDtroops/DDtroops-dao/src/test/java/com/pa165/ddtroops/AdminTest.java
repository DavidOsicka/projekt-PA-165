/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pa165.ddtroops;

import com.pa165.ddtroops.dao.AdminDAO;
import com.pa165.ddtroops.entity.Admin;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 * @author Martin Peška
 * 
 * Class AdminTest contains tests for CRUD operations 
 */

@Transactional
@ContextConfiguration("file:src/test/resources/applicationContext-dao-test.xml")
@TestExecutionListeners({TransactionalTestExecutionListener.class})
public class AdminTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    private AdminDAO adminDAO;
    
    public AdminTest() {
    }
    
    private Admin createTestSubject(){
        Admin admin = new Admin();
        admin.setName("Don Admini " + new Random().nextInt());
        return admin;
    }
    
    private void clearAdminDAO(){
        for(Admin a : this.adminDAO.retrieveAllAdmins()){
            this.adminDAO.deleteAdmin(a);
        }
    }
    
    @Test
    public void createAdmin() {
        Admin admin = this.createTestSubject();
        this.adminDAO.createAdmin(admin);
        assertEquals(this.adminDAO.retrieveAdminById(admin.getId()), admin, "Admin is not correctly created");
    }
    
    @Test
    public void updateAdmin(){
        Admin admin = this.createTestSubject();
        this.adminDAO.createAdmin(admin);
        admin.setName("Don Giovani");
        this.adminDAO.updateAdmin(admin);
        assertEquals(this.adminDAO.retrieveAdminById(admin.getId()),admin, "Admin is not correctly updated");
        
    }
    
    @Test
    public void deleteAdmin(){
        Admin admin = this.createTestSubject();
        this.adminDAO.createAdmin(admin);
        this.adminDAO.deleteAdmin(admin);
        assertNull(this.adminDAO.retrieveAdminById(admin.getId()), "Admin is not correctly deleted");
    }
    
    @Test
    public void retrieveAdminById(){
        Admin admin = this.createTestSubject();
        this.adminDAO.createAdmin(admin);
        assertNotNull(this.adminDAO.retrieveAdminById(admin.getId()), "Admin is not correctly retrieved by id");
    }
    
    @Test
    public void retrieveAdminByName(){
        Admin admin = this.createTestSubject();
        admin.setName("Test name");
        this.adminDAO.createAdmin(admin);
        assertNotNull(this.adminDAO.retrieveAdminByName("Test name"), "Admin is not correctly retrieved by name");
    }
    
    @Test
    public void retrieveAllAdmins(){
        this.clearAdminDAO();
        
        // test if adminDao is clear
        assertEquals(this.adminDAO.retrieveAllAdmins().size(), 0, "Admin DAO is not successfully clered");
        
        // creates 10 test subjects
        for(int i = 0;i<10;i++){
            this.adminDAO.createAdmin(this.createTestSubject());          
        }
        
        assertEquals(this.adminDAO.retrieveAllAdmins().size(), 10, "Admins are not successfully retrieved");
    }
    
    @Test(expectedExceptions = DataAccessException.class)
    public void isThrowingDataAccessException() {
        Admin admin = this.createTestSubject();
        admin.setName("Same name");
        Admin admin2 = this.createTestSubject();
        admin2.setName("Same name");
        
        this.adminDAO.createAdmin(admin);
        this.adminDAO.createAdmin(admin2);
    }
}
