/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.ddtroops;


import com.pa165.ddtroops.dao.TroopDAO;
import com.pa165.ddtroops.daoimpl.TroopDAOImpl;
import com.pa165.ddtroops.entity.Troop;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jakub Szotkowski
 * 
 * Test class for CRUD operations with TroopDAO
 */
public class TroopTest {
    
    /*private static TroopDAO troopDao;
    
    public TroopTest() {
        troopDao = new TroopDAOImpl();
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
    
    private Troop createStartTroop() {
        Troop troop = new Troop();
        troop.setName("Killers_" + new Random().nextInt());
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
    }*/
}
