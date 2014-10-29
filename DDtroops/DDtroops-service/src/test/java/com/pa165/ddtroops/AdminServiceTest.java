/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pa165.ddtroops;

import com.pa165.ddtroops.dao.AdminDAO;
import com.pa165.ddtroops.daoimpl.AdminDAOImpl;
import com.pa165.ddtroops.dto.AdminDTO;
import com.pa165.ddtroops.entity.Admin;
import com.pa165.ddtroops.service.AdminService;
import com.pa165.ddtroops.serviceimpl.AdminServiceImpl;
import java.util.Random;
import org.dozer.Mapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import static org.mockito.Matchers.*;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Martin Peška
 * 
 * Class AdminTest contains tests for CRUD operations 
 */
@ContextConfiguration("file:src/main/resources/applicationContext-service.xml")
public class AdminServiceTest extends AbstractTestNGSpringContextTests {
    
    @InjectMocks
    private AdminServiceImpl adminService;
    
    @Mock
    private AdminDAO adminDAO;
    
    @Autowired
    @Spy
    private Mapper mapper;
    
    @BeforeMethod
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
    
    private AdminDTO createTestSubject(){
        AdminDTO admin = new AdminDTO();
        admin.setName("Don Admini " + new Random().nextInt());
        return admin;
    }
    
//    private void clearAdminDAO(){
//        for(Admin a : this.adminDAO.retrieveAllAdmins()){
//            this.adminDAO.deleteAdmin(a);
//        }
//    }
    
    @Test
    public void createAdmin() {
        AdminDTO admin = this.createTestSubject();
        Mockito.when(adminDAO.createAdmin((Admin)notNull())).thenAnswer(new Answer<Admin>() {

            @Override
            public Admin answer(InvocationOnMock invocation) throws Throwable {
                Admin mockedAdmin = invocation.getArgumentAt(0, Admin.class);
                mockedAdmin.setId(1);
                return mockedAdmin;
            }
        });
        
        admin = this.adminService.createAdmin(admin);
        
        assertTrue(admin.getId() > 0);
    }
    
//    @Test
//    public void updateAdmin(){
//        Admin admin = this.createTestSubject();
//        this.adminDAO.createAdmin(admin);
//        admin.setName("Don Giovani");
//        this.adminDAO.updateAdmin(admin);
//        assertEquals(this.adminDAO.retrieveAdminById(admin.getId()),admin, "Admin is not correctly updated");
//        
//    }
//    
//    @Test
//    public void deleteAdmin(){
//        Admin admin = this.createTestSubject();
//        this.adminDAO.createAdmin(admin);
//        this.adminDAO.deleteAdmin(admin);
//        assertNull(this.adminDAO.retrieveAdminById(admin.getId()), "Admin is not correctly deleted");
//    }
//    
//    @Test
//    public void retrieveAdminById(){
//        Admin admin = this.createTestSubject();
//        this.adminDAO.createAdmin(admin);
//        assertNotNull(this.adminDAO.retrieveAdminById(admin.getId()), "Admin is not correctly retrieved by id");
//    }
//    
//    @Test
//    public void retrieveAdminByName(){
//        Admin admin = this.createTestSubject();
//        admin.setName("Test name");
//        this.adminDAO.createAdmin(admin);
//        assertNotNull(this.adminDAO.retrieveAdminByName("Test name"), "Admin is not correctly retrieved by name");
//    }
//    
//    @Test
//    public void retrieveAllAdmins(){
//        this.clearAdminDAO();
//        
//        // test if adminDao is clear
//        assertEquals(this.adminDAO.retrieveAllAdmins().size(), 0, "Admin DAO is not successfully clered");
//        
//        // creates 10 test subjects
//        for(int i = 0;i<10;i++){
//            this.adminDAO.createAdmin(this.createTestSubject());          
//        }
//        
//        assertEquals(this.adminDAO.retrieveAllAdmins().size(), 10, "Admins are not successfully retrieved");
//    }
    
    
}
