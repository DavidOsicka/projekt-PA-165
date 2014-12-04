/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.ddtroops;


import com.pa165.ddtroops.dao.TroopDAO;
import com.pa165.ddtroops.entity.Troop;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Jakub Szotkowski
 * 
 * Test class for CRUD operations with TroopDAO
 */
@Transactional
@ContextConfiguration("file:src/test/resources/applicationContext-dao-test.xml")
@TestExecutionListeners({TransactionalTestExecutionListener.class})
public class TroopTest extends AbstractTestNGSpringContextTests{
    @Autowired
    private TroopDAO troopDao;
    
    private final Random randomizer = new Random();
    
    public TroopTest() {
    }
    
    private Troop createStartTroop() {
        Troop troop = new Troop();
        troop.setName("Killers_" + randomizer.nextInt());
        troop.setMission("Go to White Castle and kill Big Yeti");
        troop.setAmountOfGM(150);
        return troop;
    }
    
    private Troop createOneTroop() {
        Troop t = createStartTroop();
        troopDao.createTroop(t);
        return t;
    }
    
    @Test
    public void createTroopTest(){
	Troop t = createOneTroop();
        Assert.assertEquals(troopDao.retrieveTroopById(t.getId()), t, "Expected troop is not equal.");
    }
    
    @Test
    public void updateTroopTest(){
        Troop t = createOneTroop();
        t.setMission("Find Little pony and help him kill wicked witch");
        t.setAmountOfGM(300);
        troopDao.updateTroop(t);
        Troop dbTroop = troopDao.retrieveTroopById(t.getId());
        Assert.assertEquals(dbTroop.getMission(), "Find Little pony and help him kill wicked witch", "Mission is not equal.");
        Assert.assertEquals(dbTroop.getAmountOfGM(), 300, "Golden money is not equal.");
    }
    
    @Test
    public void deleteTroopTest(){
        Troop t = createOneTroop();
        troopDao.deleteTroop(t);
        Troop dbTroop = troopDao.retrieveTroopById(t.getId());
        Assert.assertNull(dbTroop, "Troop wasn´t deleted.");
    }
    
    @Test
    public void retrieveAllTroopsTest(){
        List<Troop> allTroops = troopDao.retrieveAllTroops();
        if(allTroops.size()>0) {
            for (Troop allTroop : allTroops) {
                troopDao.deleteTroop(allTroop);
            }
        }
        allTroops = troopDao.retrieveAllTroops();
        Assert.assertEquals(allTroops.size(), 0);
        for(int i=0; i<5; i++){
            createOneTroop();
        }
        allTroops = troopDao.retrieveAllTroops();
        Assert.assertEquals(allTroops.size(), 5);
    }
    
    @Test
    public void retrieveTroopByIdTest() {
        Troop t = createOneTroop();
        Troop dbTroop = troopDao.retrieveTroopById(t.getId());
        Assert.assertNotNull(dbTroop);
    }
    
    @Test
    public void retrieveTroopByNameTest() {
        Troop t = createStartTroop();
        t.setName("Croosters");
        troopDao.createTroop(t);
        Troop dbTroop = troopDao.retrieveTroopByName("Croosters");
        Assert.assertNotNull(dbTroop);
    }
    
    @Test(expectedExceptions = DataAccessException.class)
    public void isThrowingDataAccessException() {
        Troop troop = this.createStartTroop();
        troop.setName("Same name");
        Troop troop2 = this.createStartTroop();
        troop2.setName("Same name");
        
        this.troopDao.createTroop(troop);
        this.troopDao.createTroop(troop2);
    }
}
