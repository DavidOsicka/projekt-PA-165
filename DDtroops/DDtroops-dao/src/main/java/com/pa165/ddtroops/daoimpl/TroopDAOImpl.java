package com.pa165.ddtroops.daoimpl;

import com.pa165.ddtroops.dao.TroopDAO;
import com.pa165.ddtroops.entity.Troop;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Martin Jelínek
 * 
 * Implementation of D&D troop DAO.
 */

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class TroopDAOImpl implements TroopDAO {

    @PersistenceContext
    private EntityManager em;

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
            if (troop.getId() < 1) {
                em.persist(troop);
            } else {
                em.merge(troop);
            }
        return troop;
    }

    @Override
    public Boolean deleteTroop(Troop troop) {
        try { 
            em.remove(em.contains(troop) ? troop : em.merge(troop));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<Troop> retrieveAllTroops() {
        List<Troop> troops = em.createQuery("SELECT t from Troop t", Troop.class).getResultList();
        return troops;
    }

    @Override
    public Troop retrieveTroopById(long id) {
        Query q = em.createQuery("SELECT t from Troop t WHERE t.id = :id", Troop.class);
        q.setParameter("id", id);
        Troop troop = getSingleTroop(q);
        return troop;
    }

    @Override
    public Troop retrieveTroopByName(String name) {
        try {
            Query q = em.createQuery("SELECT t from Troop t WHERE t.name = :name", Troop.class);
            q.setParameter("name", name);
            Troop troop = getSingleTroop(q);
            return troop;
        } catch (NoResultException ex) {
            return null;
        }
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
