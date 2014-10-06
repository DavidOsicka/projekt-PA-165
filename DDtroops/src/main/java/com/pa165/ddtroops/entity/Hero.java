package com.pa165.ddtroops.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author Jakub Szotkowski
 * 
 * Represent one hero from D&D troop. Hero has atributes as name, xp, roles and his troop.
 */
@Entity
public class Hero {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable=false)
    private String name;

    @ManyToMany
    private Set<Role> role = new HashSet<Role>();;

    private int xp;

    @ManyToOne
    private Troop troop = null;

    public Hero () {
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

    public Set<Role> getRole () {
        return role;
    }

    public void setRole (Set<Role> val) {
        this.role = val;
    }

    public Troop getTroop () {
        return troop;
    }

    public void setTroop (Troop val) {
        this.troop = val;
    }

    public int getXp () {
        return xp;
    }

    public void setXp (int val) {
        this.xp = val;
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
        final Hero other = (Hero) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}

