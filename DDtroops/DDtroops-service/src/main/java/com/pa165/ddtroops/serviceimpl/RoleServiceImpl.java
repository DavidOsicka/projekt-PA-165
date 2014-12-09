/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.ddtroops.serviceimpl;

import com.pa165.ddtroops.dao.RoleDAO;
import com.pa165.ddtroops.api.dto.RoleDTO;
import com.pa165.ddtroops.entity.Role;
import com.pa165.ddtroops.api.service.RoleService;
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
 * Implementation of role service interface
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService{

    @Autowired
    private Mapper mapper;
    
    @Autowired
    private RoleDAO roleDao;

    public RoleServiceImpl() {
    }

    public RoleServiceImpl(Mapper mapper, RoleDAO roleDao) {
        this.mapper = mapper;
        this.roleDao = roleDao;
    }
    
    private RoleDTO mapDTO(Role role) {
        return mapper == null || role == null ? null : mapper.map(role, RoleDTO.class);
    }
    
    private Role mapEntity(RoleDTO roleDTO) {
        return mapper == null || roleDTO == null ? null : mapper.map(roleDTO, Role.class);
    }
    
    @Override
    public RoleDTO createRole(RoleDTO role) {
        return mapDTO(roleDao.createRole(mapEntity(role)));
    }

    @Override
    public RoleDTO updateRole(RoleDTO role) {
        return mapDTO(roleDao.updateRole(mapEntity(role)));
    }

    @Override
    public Boolean deleteRole(RoleDTO role) {
        return roleDao.deleteRole(mapEntity(role));
    }

    @Override
    public List<RoleDTO> retrieveAllRoles() {
        List<RoleDTO> allRoles = new ArrayList();
        for(Role r : roleDao.retrieveAllRoles()) {
            allRoles.add(mapDTO(r));
        }
        return allRoles;
    }

    @Override
    public RoleDTO retrieveRoleById(long id) {
        return mapDTO(roleDao.retrieveRoleById(id));
    }

    @Override
    public RoleDTO retrieveRoleByName(String name) {
        return mapDTO(roleDao.retrieveRoleByName(name));
    }
    
    @Override
    public Boolean deleteAllRoles() {
        Boolean res = true;
        for(RoleDTO r : retrieveAllRoles()) {
            res &= deleteRole(r);
        }
        return res;
    }
}
