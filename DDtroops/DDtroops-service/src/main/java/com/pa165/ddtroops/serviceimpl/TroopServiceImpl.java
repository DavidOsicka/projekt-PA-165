/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.ddtroops.serviceimpl;

import com.pa165.ddtroops.dao.TroopDAO;
import com.pa165.ddtroops.api.dto.TroopDTO;
import com.pa165.ddtroops.entity.Troop;
import com.pa165.ddtroops.api.service.TroopService;
import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jakub Szotkowski
 * 
 * Implementation of Troop service interface
 */
@Service
@Transactional
public class TroopServiceImpl implements TroopService {
   
    @Autowired
    private Mapper mapper;
    
    @Autowired
    private TroopDAO troopDao;

    public TroopServiceImpl() {
    }

    public TroopServiceImpl(Mapper mapper, TroopDAO troopDao) {
        this.mapper = mapper;
        this.troopDao = troopDao;
    }
    
    private TroopDTO mapDTO(Troop troop) {
        return mapper == null || troop == null ? null : mapper.map(troop, TroopDTO.class);
    }
    
    private Troop mapEntity(TroopDTO troopDTO) {
        return mapper == null || troopDTO == null ? null : mapper.map(troopDTO, Troop.class);
    }

    @Override
    public TroopDTO createTroop(TroopDTO troop) {
        return mapDTO(troopDao.createTroop(mapEntity(troop)));
    }

    @Override
    public TroopDTO updateTroop(TroopDTO troop) {
        return mapDTO(troopDao.updateTroop(mapEntity(troop)));
    }

    @Override
    public Boolean deleteTroop(TroopDTO troop) {
        return troopDao.deleteTroop(mapEntity(troop));
    }

    @Override
    public List<TroopDTO> retrieveAllTroops() {
        List<TroopDTO> allTroops = new ArrayList();
        for(Troop t : troopDao.retrieveAllTroops()) {
            allTroops.add(mapDTO(t));
        }
        return allTroops;
    }

    @Override
    public TroopDTO retrieveTroopById(long id) {
        return mapDTO(troopDao.retrieveTroopById(id));
    }

    @Override
    public TroopDTO retrieveTroopByName(String name) {
        return mapDTO(troopDao.retrieveTroopByName(name));
    }

    @Override
    public Boolean deleteAllTroops() {
        Boolean res = true;
        for(TroopDTO t : retrieveAllTroops()) {
            res &= deleteTroop(t);
        }
        return res;
    }
    
}
