/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.ddtroops.api.service;

import com.pa165.ddtroops.api.dto.RoleDTO;
import java.util.List;

/**
 *
 * @author Jakub Szotkowski
 * 
 * Interface for Role service
 */
public interface RoleService {
    
    /**
     * Method createRole creates role in database and returns it
     * 
     * @param role role which is being saved
     * @return     role 
     */
    public RoleDTO createRole (RoleDTO role);
    
    /**
     * Method updates attributes of role in database and returns it
     * 
     * @param role role which is being updated
     * @return     role
     */
    public RoleDTO updateRole (RoleDTO role);

    /**
     * Method deletes role from database and returns it
     * 
     * @param role role which is being deleted
     * @return     role
     */
    public Boolean deleteRole (RoleDTO role);

    /**
     * Method returns all roles saved in database
     * 
     * @return     list of all saved roles 
     */
    public List<RoleDTO> retrieveAllRoles ();

    /**
     * Method returns role found by id
     * 
     * @param id id which is used to find the role
     * @return     found role
     */
    public RoleDTO retrieveRoleById (long id);
    
    /**
     * Method returns role found by unique name
     * 
     * @param name name which is used to find the role
     * @return     found role
     */
    public RoleDTO retrieveRoleByName (String name);
    
    /**
     * Delete all roles from database
     * 
     * @return true if all roles were deleted, false otherwise 
     */
    public Boolean deleteAllRoles();
}
