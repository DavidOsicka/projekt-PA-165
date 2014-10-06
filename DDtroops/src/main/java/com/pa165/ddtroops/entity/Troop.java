package com.pa165.ddtroops.entity;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Troop {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String mission;

    private long amountOfGM;
    @OneToMany(mappedBy="troop")
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

}

