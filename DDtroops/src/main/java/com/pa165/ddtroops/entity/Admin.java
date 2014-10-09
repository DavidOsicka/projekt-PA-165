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
    


    public Admin () {
    }
 
    
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

