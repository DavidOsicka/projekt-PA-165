package com.pa165.ddtroops.daoimpl;

import com.pa165.ddtroops.dao.AdminDAO;
import com.pa165.ddtroops.entity.Admin;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of AdminDAO interface.
 *
 * @author Jakub Kovařík
 */

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class AdminDAOImpl implements AdminDAO {

    @PersistenceContext
    private EntityManager em;

    public AdminDAOImpl() {
    }

    @Override
    public Admin createAdmin(Admin admin) {
        em.persist(admin);
        return admin;
    }

    @Override
    public Admin updateAdmin(Admin admin) {
        em.merge(admin);
        return admin;
    }

    @Override
    public Boolean deleteAdmin(Admin admin) {
        Admin deleteAdmin = em.find(Admin.class, admin.getId());

        try {
            em.remove(deleteAdmin);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<Admin> retrieveAllAdmins() {
        List<Admin> allAdmins = em.createQuery("SELECT a FROM Admin a", Admin.class).getResultList();
        return allAdmins;
    }

    @Override
    public Admin retrieveAdminById(long id) {
        Admin adminById = em.find(Admin.class, id);
        return adminById;
    }

    @Override
    public Admin retrieveAdminByName(String name) {
        try {
            Admin adminByName = em.createQuery(
                    "SELECT a FROM Admin a WHERE a.name=:name", Admin.class)
                    .setParameter("name", name)
                    .getSingleResult();
            return adminByName;
        } catch (NoResultException ex) {
            return null;
        }
    }

}
