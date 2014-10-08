package com.pa165.ddtroops.daoimpl;

import com.pa165.ddtroops.dao.TroopDAO;
import com.pa165.ddtroops.entity.Troop;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author Martin Jelinek
 * 
 * Implementation of D&D troop DAO.
 */
@ContextConfiguration(classes = DaoContext.class)
public class TroopDAOImpl implements TroopDAO {

    @PersistenceUnit
    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("myUnit");

    public TroopDAOImpl() {
    }

    @Override
    public Troop createTroop(Troop troop) {
        return insertOrUpdate(troop);
    }

    @Override
    public Troop updateTroop(Troop troop) {
        return insertOrUpdate(troop);
    }
    
    private Troop insertOrUpdate(Troop troop) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            if (troop.getId() < 1) {
                em.persist(troop);
            } else {
                em.merge(troop);
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
        em.close();
        return troop;
    }

    @Override
    public Boolean deleteTroop(Troop troop) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.remove(em.contains(troop) ? troop : em.merge(troop));
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            return false;
        }
        em.close();
        return true;
    }

    @Override
    public List<Troop> retrieveAllTroops() {
        EntityManager em = emf.createEntityManager();
        List<Troop> troops = em.createQuery("SELECT t from Troop t", Troop.class).getResultList();
        em.close();
        return troops;
    }

    @Override
    public Troop retrieveTroopById(long id) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT t from Troop t WHERE t.id = :id", Troop.class);
        q.setParameter("id", id);
        Troop troop = getSingleTroop(q);
        em.close();
        return troop;
    }

    @Override
    public Troop retrieveTroopByName(String name) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT t from Troop t WHERE t.name = :name", Troop.class);
        q.setParameter("name", name);
        Troop troop = getSingleTroop(q);
        em.close();
        return troop;
    }
    
    private Troop getSingleTroop(Query q) {
        Troop troop = null;
        try {
            troop = (Troop) q.getSingleResult();   
        } catch (Exception e) {
            
        }
        
        return troop;
    }

}
