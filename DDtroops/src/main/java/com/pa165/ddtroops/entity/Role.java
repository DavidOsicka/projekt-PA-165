package com.pa165.ddtroops.entity;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String description;
    
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

}

