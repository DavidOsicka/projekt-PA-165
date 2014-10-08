package com.pa165.ddtroops.dao;

import com.pa165.ddtroops.entity.Troop;
import java.util.List;

/**
 *
 * @author Martin Jelinek
 * 
 * Interface for D&D troop DAO.
 */
public interface TroopDAO {

    public Troop createTroop(Troop troop);

    public Troop updateTroop(Troop troop);

    public Boolean deleteTroop(Troop troop);

    public List<Troop> retrieveAllTroops();

    public Troop retrieveTroopById(long id);

    public Troop retrieveTroopByName(String name);

}
