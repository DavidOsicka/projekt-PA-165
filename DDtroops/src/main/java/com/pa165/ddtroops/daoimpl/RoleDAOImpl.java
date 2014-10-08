package com.pa165.ddtroops.daoimpl;


import com.pa165.ddtroops.dao.RoleDAO;
import com.pa165.ddtroops.entity.Role;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes=DaoContext.class)
public class RoleDAOImpl implements RoleDAO {

    @PersistenceUnit
    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("myUnit");
    
    public RoleDAOImpl () {
    }

    @Override
    public Role createRole (Role role) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(role);
        em.getTransaction().commit();
        em.close();
        return role;
    }

    @Override
    public Role updateRole (Role role) {
        EntityManager em = emf.createEntityManager();
        Role updateRole = em.find(Role.class, role.getId());
        em.getTransaction().begin();
        updateRole.setName(role.getName());
        updateRole.setDescription(role.getDescription());
        updateRole.setEnergy(role.getEnergy());
        updateRole.setAttack(role.getAttack());
        updateRole.setDefense(role.getDefense());
        updateRole.setHeroes(role.getHeroes());
        em.getTransaction().commit();
        em.close();
        return updateRole;
    }

    @Override
    public Boolean deleteRole (Role role) {
        try {
            EntityManager em = emf.createEntityManager();
            Role deleteRole = em.find(Role.class, role.getId());
            em.getTransaction().begin();
            em.remove(deleteRole);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Role> retrieveAllRoles () {
        EntityManager em = emf.createEntityManager();
        List<Role> allRoles = em.createQuery("SELECT r FROM Role r", Role.class).getResultList();
	em.close();
        return allRoles;
    }

    @Override
    public Role retrieveRoleById (long id) {
        EntityManager em = emf.createEntityManager();
        Role roleById = em.find(Role.class, id);
        em.close();
        return roleById;
    }

    @Override
    public Role retrieveRoleByName (String name) {
        EntityManager em = emf.createEntityManager();
        Role roleByName = em.createQuery("SELECT r FROM Role r WHERE name=:name", Role.class).setParameter("name", name).getSingleResult();
        em.close();
        return roleByName;
    }

}

