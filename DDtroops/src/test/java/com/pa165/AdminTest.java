/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pa165;

import com.pa165.ddtroops.dao.AdminDAO;
import com.pa165.ddtroops.daoimpl.AdminDAOImpl;
import com.pa165.ddtroops.entity.Admin;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 * @author Martin Peška
 * 
 */
public class AdminTest {
    private final AdminDAO adminDAO;
    
    public AdminTest(){
        this.adminDAO = new AdminDAOImpl();
    }
    
    private Admin createTestSubject(){
        Admin admin = new Admin();
        admin.setName("Don Admini");
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
    
    
}
