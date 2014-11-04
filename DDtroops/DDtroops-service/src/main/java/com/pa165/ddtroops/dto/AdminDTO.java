package com.pa165.ddtroops.dto;

import java.io.Serializable;

/**
 * @author Martin Jelínek
 *
 * Admin transfer object for D&D troops.
 */
public class AdminDTO implements Serializable {

    private long id;
    private String name;

    public AdminDTO() {
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final AdminDTO other = (AdminDTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
