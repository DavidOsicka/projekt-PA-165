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
        EntityManager em = emf.createEntityManager();
        Hero updateHero = em.find(Hero.class, hero.getId());
        em.getTransaction().begin();
        updateHero.setName(hero.getName());
        updateHero.setRole(hero.getRole());
        updateHero.setTroop(hero.getTroop());
        updateHero.setXp(hero.getXp());
        em.getTransaction().commit();
        em.close();
        return updateHero;
    }

    public Boolean deleteHero (Hero hero) {
        try {
            EntityManager em = emf.createEntityManager();
            Hero deleteHero = em.find(Hero.class, hero.getId());
            em.getTransaction().begin();
            em.remove(deleteHero);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Hero> retrieveAllHeroes () {
        EntityManager em = emf.createEntityManager();
        List<Hero> allHeroes = em.createQuery("SELECT h FROM Hero h", Hero.class).getResultList();
	em.close();
        return allHeroes;
    }

    public Hero retrieveHeroById (long id) {
        EntityManager em = emf.createEntityManager();
        Hero heroById = em.find(Hero.class, id);
        em.close();
        return heroById;
    }

    public Hero retrieveHeroByName (String name) {
        EntityManager em = emf.createEntityManager();
        Hero heroByName = em.createQuery("SELECT h FROM Hero h WHERE name=:name", Hero.class).setParameter("name", name).getSingleResult();
        em.close();
        return heroByName;
    }

}

