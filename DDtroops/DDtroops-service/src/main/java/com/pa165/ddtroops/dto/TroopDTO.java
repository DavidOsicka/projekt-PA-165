package com.pa165.ddtroops.dto;

import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author Martin Jelínek
 *
 * Troop transfer object for D&D troops.
 */
public class TroopDTO implements Serializable {

    private long id;
    private String name;
    private String mission;
    private long amountOfGM;

    private Set<HeroDTO> heroes;

    public TroopDTO() {
    }

    public long getAmountOfGM() {
        return amountOfGM;
    }

    public void setAmountOfGM(long val) {
        this.amountOfGM = val;
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

    public String getMission() {
        return mission;
    }

    public void setMission(String val) {
        this.mission = val;
    }

    public String getName() {
        return name;
    }

    public void setName(String val) {
        this.name = val;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final TroopDTO other = (TroopDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
