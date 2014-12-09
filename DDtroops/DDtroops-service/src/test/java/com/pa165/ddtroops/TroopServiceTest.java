/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.ddtroops;

import com.pa165.ddtroops.dao.TroopDAO;
import com.pa165.ddtroops.api.dto.TroopDTO;
import com.pa165.ddtroops.entity.Troop;
import com.pa165.ddtroops.serviceimpl.TroopServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.notNull;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.MockitoAnnotations.initMocks;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jakub Szotkowski
 * 
 * Test class for testing Troop service
 */
@DirtiesContext
@ContextConfiguration(locations = {"classpath:/applicationContext-service-test.xml"})
public class TroopServiceTest extends AbstractTestNGSpringContextTests{
    
    @InjectMocks   
    TroopServiceImpl troopService;
   
    @Mock
    TroopDAO troopDao;
    
    @Autowired
    @Spy
    private Mapper mapper;
    
    @BeforeMethod
    private void start(){
        initMocks(this);
        Troop troop = createNewTroop();
        Mockito.when(troopDao.createTroop(troop)).thenAnswer(new Answer<Troop>() {

            @Override
            public Troop answer(InvocationOnMock invocation) throws Throwable {
                Troop mockedTroop = invocation.getArgumentAt(0, Troop.class);
                mockedTroop.setId(1);
                return mockedTroop;
            }
        });
        
        Mockito.when(troopDao.retrieveTroopById(1)).thenAnswer(new Answer<Troop>() {

            @Override
            public Troop answer(InvocationOnMock invocation) throws Throwable {
                Troop mockedTroop = createNewTroop();
                mockedTroop.setId(1);
                return mockedTroop;
            }
        });
        
        Mockito.when(troopDao.retrieveTroopByName("Killers")).thenAnswer(new Answer<Troop>() {

            @Override
            public Troop answer(InvocationOnMock invocation) throws Throwable {
                Troop mockedTroop = createNewTroop();
                mockedTroop.setId(1);
                return mockedTroop;
            }
        });
        
        Mockito.when(troopDao.retrieveTroopByName("Crushers")).thenAnswer(new Answer<Troop>() {

            @Override
            public Troop answer(InvocationOnMock invocation) throws Throwable {
                Troop mockedTroop = createNewTroop();
                mockedTroop.setId(1);
                mockedTroop.setName("Crushers");
                mockedTroop.setMission("Kill wicked witch");
                mockedTroop.setAmountOfGM(10_000);
                return mockedTroop;
            }
        });
        
        Mockito.when(troopDao.updateTroop((Troop)notNull())).thenAnswer(new Answer<Troop>() {

            @Override
            public Troop answer(InvocationOnMock invocation) throws Throwable {
                Troop mockedTroop = invocation.getArgumentAt(0, Troop.class);
                return mockedTroop;
            }
        });
        
        Mockito.when(troopDao.deleteTroop((Troop)notNull())).thenReturn(Boolean.TRUE);
        
        Mockito.when(troopDao.retrieveAllTroops()).thenAnswer(new Answer<List<Troop>>() {

            @Override
            public List<Troop> answer(InvocationOnMock invocation) throws Throwable {
                List<Troop> troops = createGroupTroops();
                return troops;
            }
        });
    }
    
    private Troop createNewTroop() {
        Troop troop = new Troop();
        troop.setName("Killers");
        troop.setMission("Kill ogre Shrek");
        troop.setAmountOfGM(2_050);
        return troop;
    }
    
    private TroopDTO createNewTroopDTO() {
        TroopDTO troop = new TroopDTO();
        troop.setName("Killers");
        troop.setMission("Kill ogre Shrek");
        troop.setAmountOfGM(2_050);
        return troop;
    }
    
    @Test
    public void createTroopTest() {
        TroopDTO troop = createNewTroopDTO();
        troop = troopService.createTroop(troop);
        Assert.assertTrue(troop.getId() == 1);
        Assert.assertEquals(troopService.retrieveTroopById(troop.getId()), troop);
        System.out.println("Test createTroop DTO run succesfull");
    }
    
    @Test
    public void retrieveTroopByIdTest() {
        TroopDTO troop = troopService.createTroop(createNewTroopDTO());
        TroopDTO dbTroop = troopService.retrieveTroopById(troop.getId());
        Assert.assertNotNull(dbTroop);
        System.out.println("Test retrieveTroopById DTO run succesfull");
    }
    
    @Test
    public void retrieveTroopByNameTest() {
        TroopDTO troop = troopService.createTroop(createNewTroopDTO());
        TroopDTO dbTroop = troopService.retrieveTroopByName(troop.getName());
        Assert.assertNotNull(dbTroop);
        System.out.println("Test retrieveTroopByName DTO run succesfull");
    }
    
    @Test
    public void updateTroopTest() {
        TroopDTO troop = troopService.createTroop(createNewTroopDTO());
        troop.setName("Crushers");
        troop.setMission("Kill wicked witch");
        troop.setAmountOfGM(10_000);
        troopService.updateTroop(troop);
        TroopDTO dbTroop = troopService.retrieveTroopByName("Crushers");
        Assert.assertEquals(dbTroop.getName(), "Crushers");
        Assert.assertEquals(dbTroop.getMission(), "Kill wicked witch");
        Assert.assertEquals(dbTroop.getAmountOfGM(), 10_000);
        System.out.println("Test updateTroop DTO run succesfull");
    }
    
    @Test
    public void deleteTroopTest() {
        TroopDTO troop = troopService.createTroop(createNewTroopDTO());
        Assert.assertTrue(troopService.deleteTroop(troop));
        System.out.println("Test deleteTroop DTO run succesfull");
    }
    
    private List<Troop> createGroupTroops() {
        List<Troop> troops = new ArrayList();
        for(int i = 1; i < 6; i++) {
            Troop troop = createNewTroop();
            troop.setName(troop.getName()+"_"+i);
            troop.setId(i);
            troops.add(troop);
        }
        return troops;
    }
    
    private void createGroupTroopsDTO() {
        for(int i = 1; i < 6; i++) {
            TroopDTO troop = troopService.createTroop(createNewTroopDTO());
            troop.setName(troop.getName()+"_"+i);
            troop.setId(i);
            troopService.updateTroop(troop);
        }
    }
    
    @Test
    public void retrieveAllTroopsTest() {
        createGroupTroopsDTO();
        List<TroopDTO> troops;    
        troops = troopService.retrieveAllTroops();
        Assert.assertEquals(troops.size(), 5);
        System.out.println("Test retrieveAllTroops DTO run succesfull");
    }
    
    @Test
    public void deleteAllTroopsTest() {
        createGroupTroopsDTO();
        Assert.assertTrue(troopService.deleteAllTroops());
        System.out.println("Test deleteAllTroops DTO run succesfull");
    }
    
}
