package com.pa165.ddtroops.dto;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Martin Jelínek
 *
 * Role transfer object for D&D troops.
 */
public class RoleDTO implements Serializable {

    private long id;
    private String name;
    private String description;
    private int energy;
    private int attack;
    private int defense;

    private Set<HeroDTO> heroes;

    public RoleDTO() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String val) {
        this.description = val;
    }

    public Set<HeroDTO> getHeroes() {
        return heroes;
    }

    public void setHeroes(Set<HeroDTO> val) {
        this.heroes = val;
    }

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }

    public String getName() {
        return name;
    }

    public void setName(String val) {
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

}
