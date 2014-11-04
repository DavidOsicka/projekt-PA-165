/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.ddtroops;

import com.pa165.ddtroops.dao.HeroDAO;
import com.pa165.ddtroops.daoimpl.HeroDAOImpl;
import com.pa165.ddtroops.dto.HeroDTO;
import com.pa165.ddtroops.entity.Hero;
import com.pa165.ddtroops.service.HeroService;
import com.pa165.ddtroops.serviceimpl.HeroServiceImpl;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.MockitoAnnotations.initMocks;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jakub
 */
@ContextConfiguration(locations = {"classpath:/applicationContext-service.xml"})
//@ContextConfiguration("file:src/main/resources/applicationContext-service.xml")
public class HeroServiceTest extends AbstractTestNGSpringContextTests {
    
    @InjectMocks       
    HeroServiceImpl heroService;
   
    @Mock
    HeroDAO heroDao;
    
    @Autowired
    @Spy
    private Mapper mapper;
    
    @BeforeMethod
    private void start(){
        initMocks(this);
        Hero h = createNewHero();
        Mockito.when(heroDao.createHero(h)).thenAnswer(new Answer<Hero>() {

            @Override
            public Hero answer(InvocationOnMock invocation) throws Throwable {
                Hero mockedHero = invocation.getArgumentAt(0, Hero.class);
                mockedHero.setId(1);
                return mockedHero;
            }
        });
    }
    
    private Hero createNewHero() {
        Hero h = new Hero();
        h.setName("Hrdina");
        h.setRace("orc");
        return h;
    }
    
    private HeroDTO createNewHeroDTO() {
        HeroDTO h = new HeroDTO();
        h.setName("Hrdina");
        h.setRace("orc");
        return h;
    }
    
    @Test
    public void createHeroTest() {
        HeroDTO h = createNewHeroDTO();
        System.out.println(heroService);
        h = heroService.createHero(h);
        Assert.assertTrue(h.getId() == 1);
        System.out.println("Test probehl v poradku, id hrdiny je: " + h.getId());
    }
}
