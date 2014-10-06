package com.pa165.ddtroops.daoimpl;


import com.pa165.ddtroops.dao.HeroDAO;
import com.pa165.ddtroops.entity.Hero;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes=DaoContext.class)
public class HeroDAOImpl implements HeroDAO {

    @PersistenceUnit
    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("myUnit");
    
    public HeroDAOImpl () {
    }

    /**
     *
     * @param hero
     * @return
     */
    @Override
    public Hero createHero (Hero hero) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(hero);
        em.getTransaction().commit();
        em.close();
        return hero;
    }

    public Hero updateHero (Hero hero) {
        return null;
    }

    public Boolean deleteHero (Hero hero) {
        return null;
    }

    public List<Hero> retrieveAllHeroes () {
        return null;
    }

    public Hero retrieveHeroById (long id) {
        return null;
    }

    public Hero retrieveHeroByName (String name) {
        return null;
    }

}

