/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.ddtroops.api.service;

import com.pa165.ddtroops.api.dto.TroopDTO;
import java.util.List;

/**
 *
 * @author Jakub Szotkowski
 * 
 * Interface for Troop service
 */
public interface TroopService {
    
    /**
     * Insert new troop into database.
     * 
     * @param troop troop to be inserted
     * @return newly created troop
     */
    public TroopDTO createTroop(TroopDTO troop);

    /**
     * Update troop, that has already been persisted, in database.
     * 
     * @param troop troop to be updated
     * @return updated troop
     */
    public TroopDTO updateTroop(TroopDTO troop);

    /**
     * Delete troop from database.
     * 
     * @param troop troop to be deleted
     * @return true if troop is deleted, false otherwise
     */
    public Boolean deleteTroop(TroopDTO troop);

    /**
     * Retrieve list of all troops from database.
     * 
     * @return list of troops
     */
    public List<TroopDTO> retrieveAllTroops();

    /**
     * Retrieve one troop with unique id.
     * 
     * @param id troop id
     * @return troop with given id or null if troop doesn't exist
     */
    public TroopDTO retrieveTroopById(long id);

    /**
     * Retrieve one troop with unique name.
     * @param name troop name
     * @return troop with given name or null if troop doesn't exist
     */
    public TroopDTO retrieveTroopByName(String name);
    
    /**
     * Delete all troops from database.
     * 
     * @return true if all troops were deleted, false otherwise 
     */
    public Boolean deleteAllTroops();
    
}
