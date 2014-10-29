package com.pa165.ddtroops.daoimpl;


import com.pa165.ddtroops.dao.HeroDAO;
import com.pa165.ddtroops.entity.Hero;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author Jakub Szotkowski
 * 
 * Implementation of HeroDao interface.
 */
//@ContextConfiguration(classes=DaoContext.class)
public class HeroDAOImpl implements HeroDAO {

    //@PersistenceUnit
    //public EntityManagerFactory emf = Persistence.createEntityManagerFactory("myUnit");
    @PersistenceContext
    private EntityManager em;
    
    public HeroDAOImpl () {
    }

    @Override
    public Hero createHero (Hero hero) {
        //EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(hero);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        em.close();
        return hero;
    }

    @Override
    public Hero updateHero (Hero hero) {
        //EntityManager em = emf.createEntityManager();
        Hero updateHero = em.find(Hero.class, hero.getId());
        try {
            em.getTransaction().begin();
            em.merge(hero);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        em.close();
        return updateHero;
    }

    @Override
    public Boolean deleteHero (Hero hero) {
        //EntityManager em = emf.createEntityManager();
        Hero deleteHero = em.find(Hero.class, hero.getId());
        try {
            em.getTransaction().begin();
            em.remove(deleteHero);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            em.close();
            return false;
        }
    }

    @Override
    public List<Hero> retrieveAllHeroes () {
        //EntityManager em = emf.createEntityManager();
        List<Hero> allHeroes = em.createQuery("SELECT h FROM Hero h", Hero.class).getResultList();
	em.close();
        return allHeroes;
    }

    @Override
    public Hero retrieveHeroById (long id) {
        //EntityManager em = emf.createEntityManager();
        Hero heroById = em.find(Hero.class, id);
        em.close();
        return heroById;
    }

    @Override
    public Hero retrieveHeroByName (String name) {
        //EntityManager em = emf.createEntityManager();
        Hero heroByName = em.createQuery("SELECT h FROM Hero h WHERE h.name=:name", Hero.class).setParameter("name", name).getSingleResult();
        em.close();
        return heroByName;
    }

}

