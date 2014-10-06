/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165;


import com.pa165.ddtroops.daoimpl.TroopDAOImpl;
import com.pa165.ddtroops.entity.Troop;
import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jakub
 */
public class TroopTest {
    
    public TroopTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

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
    
    @Test
    public void createNewTroop(){
	Troop t = new Troop();
        t.setName("Killers");
        t.setMission("Go to White Castle and kill Big Yeti");
        t.setAmountOfGM(150);
        TroopDAOImpl tDAO = new TroopDAOImpl();
        tDAO.createTroop(t);
        Assert.assertEquals(t.getId(), 1);
        Assert.assertEquals(t.getName(), "Killers");
    }
}
