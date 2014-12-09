/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pa165.ddtroops;

import com.pa165.ddtroops.dao.AdminDAO;
import com.pa165.ddtroops.api.dto.AdminDTO;
import com.pa165.ddtroops.entity.Admin;
import com.pa165.ddtroops.serviceimpl.AdminServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import static org.mockito.Matchers.*;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.mockito.MockitoAnnotations.*;
import org.springframework.test.annotation.DirtiesContext;

/**
 * @author Martin Jelínek and Jakub Szotkowski
 * 
 * Test class for testing Admin service 
 */
@DirtiesContext
@ContextConfiguration(locations = {"classpath:/applicationContext-service-test.xml"})
public class AdminServiceTest extends AbstractTestNGSpringContextTests {
    
    @InjectMocks
    private AdminServiceImpl adminService;
    
    @Mock
    private AdminDAO adminDAO;
    
    @Autowired
    @Spy
    private Mapper mapper;
    
    @BeforeMethod
    public void initMocksObject() {
        initMocks(this);
        Admin admin = createNewAdmin();
        Mockito.when(adminDAO.createAdmin(admin)).thenAnswer(new Answer<Admin>() {
           
            @Override
            public Admin answer(InvocationOnMock invocation) throws Throwable {
                Admin mockedAdmin = invocation.getArgumentAt(0, Admin.class);
                mockedAdmin.setId(1);
                return mockedAdmin;
            }
        });
        
        Mockito.when(adminDAO.retrieveAdminById(1)).thenAnswer(new Answer<Admin>() {

            @Override
            public Admin answer(InvocationOnMock invocation) throws Throwable {
                Admin mockedAdmin = createNewAdmin();
                mockedAdmin.setId(1);
                return mockedAdmin;
            }
        });
        
        Mockito.when(adminDAO.retrieveAdminByName("Admin")).thenAnswer(new Answer<Admin>() {

            @Override
            public Admin answer(InvocationOnMock invocation) throws Throwable {
                Admin mockedAdmin = createNewAdmin();
                mockedAdmin.setId(1);
                return mockedAdmin;
            }
        });
        
        Mockito.when(adminDAO.retrieveAdminByName("Don Admini")).thenAnswer(new Answer<Admin>() {

            @Override
            public Admin answer(InvocationOnMock invocation) throws Throwable {
                Admin mockedAdmin = createNewAdmin();
                mockedAdmin.setId(1);
                mockedAdmin.setName("Don Admini");
                return mockedAdmin;
            }
        });
        
        Mockito.when(adminDAO.updateAdmin((Admin)notNull())).thenAnswer(new Answer<Admin>() {

            @Override
            public Admin answer(InvocationOnMock invocation) throws Throwable {
                Admin mockedAdmin = invocation.getArgumentAt(0, Admin.class);
                return mockedAdmin;
            }
        });
        
        Mockito.when(adminDAO.deleteAdmin((Admin)notNull())).thenReturn(Boolean.TRUE);
        
        Mockito.when(adminDAO.retrieveAllAdmins()).thenAnswer(new Answer<List<Admin>>() {

            @Override
            public List<Admin> answer(InvocationOnMock invocation) throws Throwable {
                List<Admin> admins = createGroupAdmins();
                return admins;
            }
        });
    }
    
    private Admin createNewAdmin() {
        Admin admin = new Admin();
        admin.setName("Admin");
        return admin;
    }
    
    private AdminDTO createNewAdminDTO() {
        AdminDTO admin = new AdminDTO();
        admin.setName("Admin");
        return admin;
    }
    
    @Test
    public void createAdminTest() {
        AdminDTO admin = createNewAdminDTO();
        admin = adminService.createAdmin(admin);
        Assert.assertTrue(admin.getId() == 1);
        Assert.assertEquals(adminService.retrieveAdminById(admin.getId()), admin);
        System.out.println("Test createAdmin DTO run succesfull");
    }
    
    @Test
    public void retrieveAdminByIdTest() {
        AdminDTO admin = adminService.createAdmin(createNewAdminDTO());
        AdminDTO dbAdmin = adminService.retrieveAdminById(admin.getId());
        Assert.assertNotNull(dbAdmin);
        System.out.println("Test retrieveAdminById DTO run succesfull");
    }
    
    @Test
    public void retrieveAdminByNameTest() {
        AdminDTO admin = adminService.createAdmin(createNewAdminDTO());
        AdminDTO dbAdmin = adminService.retrieveAdminByName(admin.getName());
        Assert.assertNotNull(dbAdmin);
        System.out.println("Test retrieveAdminByName DTO run succesfull");
    }
    
    @Test
    public void updateAdminTest() {
        AdminDTO admin = adminService.createAdmin(createNewAdminDTO());
        admin.setName("Don Admini");
        adminService.updateAdmin(admin);
        AdminDTO dbAdmin = adminService.retrieveAdminByName("Don Admini");
        Assert.assertEquals(dbAdmin.getName(), "Don Admini");
        System.out.println("Test updateAdmin DTO run succesfull");
    }
    
    @Test
    public void deleteAdminTest() {
        AdminDTO admin = adminService.createAdmin(createNewAdminDTO());
        Assert.assertTrue(adminService.deleteAdmin(admin));
        System.out.println("Test deleteAdmin DTO run succesfull");
    }
    
    private List<Admin> createGroupAdmins() {
        List<Admin> admins = new ArrayList();
        for(int i = 1; i < 6; i++) {
            Admin admin = createNewAdmin();
            admin.setName(admin.getName()+"_"+i);
            admin.setId(i);
            admins.add(admin);
        }
        return admins;
    }
    
    private void createGroupAdminsDTO() {
        for(int i = 1; i < 6; i++) {
            AdminDTO admin = adminService.createAdmin(createNewAdminDTO());
            admin.setName(admin.getName()+"_"+i);
            admin.setId(i);
            adminService.updateAdmin(admin);
        }
    }
    
    @Test
    public void retrieveAllAdminsTest() {
        createGroupAdminsDTO();
        List<AdminDTO> admins;    
        admins = adminService.retrieveAllAdmins();
        Assert.assertEquals(admins.size(), 5);
        System.out.println("Test retrieveAllAdmins DTO run succesfull");
    }
    
    @Test
    public void deleteAllAdminsTest() {
        createGroupAdminsDTO();
        Assert.assertTrue(adminService.deleteAllAdmins());
        System.out.println("Test deleteAllAdmins DTO run succesfull");
    }
}
