package com.pa165.ddtroops.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Martin Jelínek
 * 
 * Represent one D&D troop. Hero has atributes as name, mission and amount of golden money.
 * Every troup constists of several heroes.
 */
@Entity
public class Troop {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String mission;

    @Column(nullable = false)
    private long amountOfGM;
    
    @OneToMany(mappedBy = "troop")
    private Set<Hero> heroes;

    public Troop () {
    }

    public long getAmountOfGM () {
        return amountOfGM;
    }

    public void setAmountOfGM (long val) {
        this.amountOfGM = val;
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

    public String getMission () {
        return mission;
    }

    public void setMission (String val) {
        this.mission = val;
    }

    public String getName () {
        return name;
    }

    public void setName (String val) {
        this.name = val;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Troop other = (Troop) obj;
        
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }
}

