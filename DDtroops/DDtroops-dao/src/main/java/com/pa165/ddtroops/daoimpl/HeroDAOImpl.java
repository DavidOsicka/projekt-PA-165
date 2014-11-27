package com.pa165.ddtroops.daoimpl;


import com.pa165.ddtroops.dao.HeroDAO;
import com.pa165.ddtroops.entity.Hero;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jakub Szotkowski
 * 
 * Implementation of HeroDao interface.
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class HeroDAOImpl implements HeroDAO {

    
    @PersistenceContext
    private EntityManager em;
    
    public HeroDAOImpl () {
    }

    @Override
    public Hero createHero (Hero hero) {
        em.persist(hero);
        return hero;
    }

    @Override
    public Hero updateHero (Hero hero) {        
        em.merge(hero); 
        return hero;
    }

    @Override
    public Boolean deleteHero (Hero hero) {
        Hero deleteHero = em.find(Hero.class, hero.getId());
        try {
            em.remove(deleteHero);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<Hero> retrieveAllHeroes () {
        List<Hero> allHeroes = em.createQuery("SELECT h FROM Hero h", Hero.class).getResultList();
        return allHeroes;
    }

    @Override
    public Hero retrieveHeroById (long id) {
        Hero heroById = em.find(Hero.class, id);
        return heroById;
    }

    @Override
    public Hero retrieveHeroByName (String name) {
        try {
            Hero heroByName = em.createQuery("SELECT h FROM Hero h WHERE h.name=:name", Hero.class)
                    .setParameter("name", name).getSingleResult();
            return heroByName;
        } catch (NoResultException ex) {
            return null;
        }
    }

}

