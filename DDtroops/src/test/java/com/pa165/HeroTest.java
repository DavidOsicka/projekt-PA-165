/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165;

import com.pa165.ddtroops.daoimpl.HeroDAOImpl;
import com.pa165.ddtroops.entity.Hero;
import java.util.List;
import javax.persistence.EntityManager;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author Jakub
 */
public class HeroTest {
    
    public HeroTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @org.testng.annotations.BeforeClass
    public static void setUpClass() throws Exception {
    }

    @org.testng.annotations.AfterClass
    public static void tearDownClass() throws Exception {
    }

    @org.testng.annotations.BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @org.testng.annotations.AfterMethod
    public void tearDownMethod() throws Exception {
    }
    
    @Test
	public void createOneHero(){
		Hero h = new Hero();
                h.setName("Ozak");
                h.setXp(1000);
                HeroDAOImpl impl = new HeroDAOImpl();
                impl.createHero(h);
                System.out.println("Hrdina se jménem: " + h.getName() + " má ID: " + h.getId());
	}
}
