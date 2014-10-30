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

}
