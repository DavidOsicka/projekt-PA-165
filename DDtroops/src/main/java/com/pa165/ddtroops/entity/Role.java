package com.pa165.ddtroops.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/** 
 * @author Martin Peška
 * 
 * Represents role entity in D&D troops. Hero has following attributes: name, 
 * description, energy, attack, defense and  set of heroes with the role.
 */
@Entity
public class Role {
    @Id
    @GeneratedValue
    private long id;
    
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;
    
    private int energy;
    
    private int attack;
    
    private int defense;
    
    @ManyToMany
    private Set<Hero> heroes;

    public Role () {
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String val) {
        this.description = val;
    }

    public Set<Hero> getHeroes () {
        return heroes;
    }

    public void setHeroes (Set<Hero> val) {
        this.heroes = val;
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

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Role other = (Role) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    

}

