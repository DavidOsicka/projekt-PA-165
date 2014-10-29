package com.pa165.ddtroops.daoimpl;


import com.pa165.ddtroops.dao.RoleDAO;
import com.pa165.ddtroops.entity.Role;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import org.springframework.test.context.ContextConfiguration;


/**
 * @author Martin Peška
 * 
 * Class RoleDAOImpl implements RoleDAO interface
 */
//@ContextConfiguration(classes=DaoContext.class)
public class RoleDAOImpl implements RoleDAO {

    //@PersistenceUnit
    //public EntityManagerFactory emf = Persistence.createEntityManagerFactory("myUnit");
    @PersistenceContext
    private EntityManager em;
    
    public RoleDAOImpl () {
    }

    @Override
    public Role createRole (Role role) {
        //EntityManager em = emf.createEntityManager();
        
        try{
            em.getTransaction().begin();
            em.persist(role);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
        }
        
        em.close();
        return role;
    }

    @Override
    public Role updateRole (Role role) {
        //EntityManager em = emf.createEntityManager();
        
        try{
            em.getTransaction().begin();
            em.merge(role);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
        }
        em.close();
        return role;
    }

    @Override
    public Boolean deleteRole (Role role) {
        //EntityManager em = emf.createEntityManager();
        Role deleteRole = em.find(Role.class, role.getId());
        
        try {       
            em.getTransaction().begin();
            em.remove(deleteRole);
            em.getTransaction().commit();     
        } catch (Exception e) {
            em.getTransaction().rollback();
            em.close();
            return false;
        }
        em.close();
        return true;
    }

    @Override
    public List<Role> retrieveAllRoles () {
        //EntityManager em = emf.createEntityManager();
        List<Role> allRoles = em.createQuery("SELECT r FROM Role r", Role.class).getResultList();
	em.close();
        return allRoles;
    }

    @Override
    public Role retrieveRoleById (long id) {
        //EntityManager em = emf.createEntityManager();
        Role roleById = em.find(Role.class, id);
        em.close();
        return roleById;
    }

    @Override
    public Role retrieveRoleByName (String name) {
        //EntityManager em = emf.createEntityManager();
        Role roleByName = em.createQuery("SELECT r FROM Role r WHERE name=:name", Role.class).setParameter("name", name).getSingleResult();
        em.close();
        return roleByName;
    }

}

