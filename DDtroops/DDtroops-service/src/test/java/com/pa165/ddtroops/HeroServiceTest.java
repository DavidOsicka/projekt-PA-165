/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.ddtroops;

import com.pa165.ddtroops.dao.HeroDAO;
import com.pa165.ddtroops.dto.HeroDTO;
import com.pa165.ddtroops.entity.Hero;
import com.pa165.ddtroops.serviceimpl.HeroServiceImpl;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jakub Szotkowski
 * 
 * Test class for testing Hero service
 */
@ContextConfiguration(locations = {"classpath:/applicationContext-service.xml"})
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
        
        Mockito.when(heroDao.retrieveHeroById(1)).thenAnswer(new Answer<Hero>() {

            @Override
            public Hero answer(InvocationOnMock invocation) throws Throwable {
                Hero mockedHero = createNewHero();
                mockedHero.setId(1);
                return mockedHero;
            }
        });
        
        Mockito.when(heroDao.retrieveHeroByName("Hrdina")).thenAnswer(new Answer<Hero>() {

            @Override
            public Hero answer(InvocationOnMock invocation) throws Throwable {
                Hero mockedHero = createNewHero();
                mockedHero.setId(1);
                return mockedHero;
            }
        });
        
        Mockito.when(heroDao.retrieveHeroByName("Odin")).thenAnswer(new Answer<Hero>() {

            @Override
            public Hero answer(InvocationOnMock invocation) throws Throwable {
                Hero mockedHero = createNewHero();
                mockedHero.setId(1);
                mockedHero.setName("Odin");
                mockedHero.setXp(100_000);
                return mockedHero;
            }
        });
        
        Mockito.when(heroDao.updateHero((Hero)notNull())).thenAnswer(new Answer<Hero>() {

            @Override
            public Hero answer(InvocationOnMock invocation) throws Throwable {
                Hero mockedHero = invocation.getArgumentAt(0, Hero.class);
                return mockedHero;
            }
        });
        
        Mockito.when(heroDao.deleteHero((Hero)notNull())).thenReturn(Boolean.TRUE);
        
        Mockito.when(heroDao.retrieveAllHeroes()).thenAnswer(new Answer<List<Hero>>() {

            @Override
            public List<Hero> answer(InvocationOnMock invocation) throws Throwable {
                List<Hero> heroes = createGroupHeroes();
                return heroes;
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
        h = heroService.createHero(h);
        Assert.assertTrue(h.getId() == 1);
        Assert.assertEquals(heroService.retrieveHeroById(h.getId()), h);
        System.out.println("Test createHero DTO run succesfull");
    }
    
    @Test
    public void retrieveHeroByIdTest() {
        HeroDTO h = heroService.createHero(createNewHeroDTO());
        HeroDTO dbHero = heroService.retrieveHeroById(h.getId());
        Assert.assertNotNull(dbHero);
        System.out.println("Test retrieveHeroById DTO run succesfull");
    }
    
    @Test
    public void retrieveHeroByNameTest() {
        HeroDTO h = heroService.createHero(createNewHeroDTO());
        HeroDTO dbHero = heroService.retrieveHeroByName(h.getName());
        Assert.assertNotNull(dbHero);
        System.out.println("Test retrieveHeroByName DTO run succesfull");
    }
    
    @Test
    public void updateHeroTest() {
        HeroDTO h = heroService.createHero(createNewHeroDTO());
        h.setName("Odin");
        h.setXp(100_000);
        heroService.updateHero(h);
        HeroDTO dbHero = heroService.retrieveHeroByName("Odin");
        Assert.assertEquals(dbHero.getName(), "Odin");
        Assert.assertEquals(dbHero.getXp(), 100_000);
        System.out.println("Test updateHero DTO run succesfull");
    }
    
    @Test
    public void deleteHeroTest() {
        HeroDTO h = heroService.createHero(createNewHeroDTO());
        Assert.assertTrue(heroService.deleteHero(h));
        System.out.println("Test deleteHero DTO run succesfull");
    }
    
    private List<Hero> createGroupHeroes() {
        List<Hero> heroes = new ArrayList();
        for(int i = 1; i < 6; i++) {
            Hero h = createNewHero();
            h.setName(h.getName()+"_"+i);
            h.setId(i);
            heroes.add(h);
        }
        return heroes;
    }
    
    private void createGroupHeroesDTO() {
        for(int i = 1; i < 6; i++) {
            HeroDTO h = heroService.createHero(createNewHeroDTO());
            h.setName(h.getName()+"_"+i);
            h.setId(i);
            heroService.updateHero(h);
        }
    }
    
    @Test
    public void retrieveAllHeroesTest() {
        createGroupHeroesDTO();
        List<HeroDTO> heroes;    
        heroes = heroService.retrieveAllHeroes();
        Assert.assertEquals(heroes.size(), 5);
        System.out.println("Test retrieveAllHeroes DTO run succesfull");
    }
    
    @Test
    public void deleteAllHeroesTest() {
        createGroupHeroesDTO();
        Assert.assertTrue(heroService.deleteAllHeroes());
        System.out.println("Test deleteAllHeroes DTO run succesfull");
    }
}
