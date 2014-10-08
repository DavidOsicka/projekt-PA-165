package com.pa165.ddtroops.entity;

import com.pa165.ddtroops.daoimpl.HeroDAOImpl;
import com.pa165.ddtroops.daoimpl.RoleDAOImpl;
import com.pa165.ddtroops.daoimpl.TroopDAOImpl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/** 
 * @author Jakub Kovařík
 * 
 * Represents Admin entity in D&D troops. Admin has attributes as name, roleDAO, heroDAO. TroopDAO.
 * Admin manages all entities.
 */

@Entity
public class Admin {

    @Id
    @GeneratedValue
    private long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    //vyresit jake anotace? jestli nejake? a jestli ma smysl tyto zde mit 
   // @Column(nullable = false)
    //private RoleDAOImpl roleDAO;
    //@Column(nullable = false)
    //private HeroDAOImpl heroDAO;
    //@Column(nullable = false)
    //private TroopDAOImpl troopDAO;

    public Admin () {
    }
    /*
    public RoleDAOImpl getRoleDAO() {
        return roleDAO;
    }

    public void setRoleDAO(RoleDAOImpl roleDAO) {
        this.roleDAO = roleDAO;
    }

    public HeroDAOImpl getHeroDAO() {
        return heroDAO;
    }

    public void setHeroDAO(HeroDAOImpl heroDAO) {
        this.heroDAO = heroDAO;
    }
    public TroopDAOImpl getTroopDAO() {
        return troopDAO;
    }

    public void setTroopDAO(TroopDAOImpl troopDAO) {
        this.troopDAO = troopDAO;
    }

    */
    public long getId () {
        return id;
    }

    public void setId (long val) {
        this.id = val;
    }

    public String getName () {
        return name;
    }

    public void setName (String val) {
        this.name = val;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Admin other = (Admin) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}

