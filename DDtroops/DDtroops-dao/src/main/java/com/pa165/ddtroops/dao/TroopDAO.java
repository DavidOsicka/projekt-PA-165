package com.pa165.ddtroops.dao;

import com.pa165.ddtroops.entity.Troop;
import java.util.List;

/**
 *
 * @author Martin Jelínek
 * 
 * Interface for D&D troop DAO.
 */
public interface TroopDAO {

    /**
     * Insert new troop into database.
     * 
     * @param troop troop to be inserted
     * @return newly created troop
     */
    public Troop createTroop(Troop troop);

    /**
     * Update troop, that has already been persisted, in database.
     * 
     * @param troop troop to be updated
     * @return updated troop
     */
    public Troop updateTroop(Troop troop);

    /**
     * Delete troop from database.
     * 
     * @param troop troop to be deleted
     * @return true if troop is deleted, false otherwise
     */
    public Boolean deleteTroop(Troop troop);

    /**
     * Retrieve list of all troops from database.
     * 
     * @return list of troops
     */
    public List<Troop> retrieveAllTroops();

    /**
     * Retrieve one troop with unique id.
     * 
     * @param id troop id
     * @return troop with given id or null if troop doesn't exist
     */
    public Troop retrieveTroopById(long id);

    /**
     * Retrieve one troop with unique name.
     * @param name troop name
     * @return troop with given name or null if troop doesn't exist
     */
    public Troop retrieveTroopByName(String name);

}
