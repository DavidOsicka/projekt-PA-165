/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.ddtroops;

import com.pa165.ddtroops.dao.HeroDAO;
import com.pa165.ddtroops.daoimpl.HeroDAOImpl;
import com.pa165.ddtroops.entity.Hero;
import java.util.Date;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author Martin Jelínek
 */
public class HeroTest {
    
    /*private final HeroDAO heroDAO;

    public HeroTest() {
        heroDAO = new HeroDAOImpl();
    }

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

    private Hero getDummyHero() {
        Hero h = new Hero();
        h.setName("Ozak" + new Date().getTime());
        h.setRace("Orc");
        h.setXp(1000);
        return h;
    }
    
    private Hero insertBasicHero() {
        Hero h = getDummyHero();
        heroDAO.createHero(h);
        return h;
    }
    
    @Test
    public void createHero() {
        Hero h = insertBasicHero();
        assertEquals(heroDAO.retrieveHeroById(h.getId()), h, "Hero is not correctly inserted");
    }
    
    @Test
    public void updateHero() {
        Hero h = insertBasicHero();
        h.setName("Odin");
        h.setXp(100000);
        heroDAO.updateHero(h);
        Hero dbHero = heroDAO.retrieveHeroById(h.getId());
        assertEquals(dbHero.getName(), "Odin");
        assertEquals(dbHero.getXp(), 100000);
    }
    
    @Test
    public void deleteHero() {
        Hero h = insertBasicHero();
        heroDAO.deleteHero(h);
        assertNull(heroDAO.retrieveHeroById(h.getId()));
    }
    
    @Test
    public void retrieveHeroById() {
        Hero h = insertBasicHero();
        Hero dbHero = heroDAO.retrieveHeroById(h.getId());
        assertNotNull(dbHero);
    }
    
    @Test
    public void retrieveHeroByName() {
        Hero h = getDummyHero();
        h.setName("Special named hero");
        heroDAO.createHero(h);
        Hero dbHero = heroDAO.retrieveHeroByName("Special named hero");
        assertNotNull(dbHero);
    }
    
    @Test
    public void retrieveAllHeroes() {
        List<Hero> allHeroes = heroDAO.retrieveAllHeroes();
        for (Hero oldHero : allHeroes) {
            heroDAO.deleteHero(oldHero);
        }
        
        allHeroes = heroDAO.retrieveAllHeroes();
        assertEquals(0, allHeroes.size());
        
        for (int i = 0; i < 10; i++) {
            insertBasicHero();
        }
        allHeroes = heroDAO.retrieveAllHeroes();
        assertEquals(10, allHeroes.size());
    }*/
}
