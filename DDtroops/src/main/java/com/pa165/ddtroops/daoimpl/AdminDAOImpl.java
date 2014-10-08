package com.pa165.ddtroops.daoimpl;


import com.pa165.ddtroops.dao.AdminDAO;
import com.pa165.ddtroops.entity.Admin;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
import org.springframework.test.context.ContextConfiguration;

/**
 * 
 * @author Jakub Kovařík
 */

@ContextConfiguration(classes=DaoContext.class)
public class AdminDAOImpl implements AdminDAO {
    
    @PersistenceUnit
    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("myUnit");
    
    public AdminDAOImpl () {
    }

    @Override
    public Admin createAdmin (Admin admin) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(admin);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
        }
        
        em.close();        
        return admin;
    }

    @Override
    public Admin updateAdmin (Admin admin) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(admin);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
        }
        
        em.close();
        return admin;
    }

    @Override
    public Boolean deleteAdmin (Admin admin) {
        EntityManager em = emf.createEntityManager();
        Admin deleteAdmin = em.find(Admin.class, admin.getId());
        
        try{
            em.getTransaction().begin();
            em.remove(deleteAdmin);
            em.getTransaction().commit();
        }catch(Exception e){
            em.getTransaction().rollback();
            em.close();
            return false;
        }
        em.close();
        return true;
    }

    @Override
    public List<Admin> retrieveAllAdmins () {
        EntityManager em = emf.createEntityManager();
        List<Admin> allAdmins = em.createQuery("SELECT a FROM Admin a", Admin.class).getResultList();
        em.close();
        return allAdmins;
    }

    @Override
    public Admin retrieveAdminById (long id) {
        EntityManager em = emf.createEntityManager();
        Admin adminById = em.find(Admin.class, id);
        em.close();
        return adminById;
    }

    @Override
    public Admin retrieveAdminByName (String name) {
        EntityManager em = emf.createEntityManager();
        //Admin adminByName = em.find(Admin.class, name);
        Admin adminByName = em.createQuery("SELECT a FROM Admin a WHERE name=:name",Admin.class).setParameter("name", name).getSingleResult();
        em.close();
        return adminByName;
    }

}

