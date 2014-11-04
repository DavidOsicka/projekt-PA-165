package com.pa165.ddtroops.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Martin Jelínek
 *
 * Hero transfer object for D&D troops.
 */

public class HeroDTO implements Serializable {

    private long id;
    private String name;
    private Set<RoleDTO> role = new HashSet<RoleDTO>();
    private int xp;

    private TroopDTO troop = null;

    private String race;

    public HeroDTO() {
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

    public Set<RoleDTO> getRole() {
        return role;
    }

    public void setRole(Set<RoleDTO> val) {
        this.role = val;
    }

    public TroopDTO getTroop() {
        return troop;
    }

    public void setTroop(TroopDTO val) {
        this.troop = val;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int val) {
        this.xp = val;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final HeroDTO other = (HeroDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
