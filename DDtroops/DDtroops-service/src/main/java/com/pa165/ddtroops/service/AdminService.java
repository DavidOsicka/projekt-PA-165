package com.pa165.ddtroops.service;

import com.pa165.ddtroops.dto.AdminDTO;
import java.util.List;

/**
 *
 * @author Martin Jelínek
 * 
 * Interface for D&D admin service
 */
public interface AdminService {
    
    /**
     * Insert new admin into database.
     * 
     * @param admin admin to be inserted
     * @return newly created admin
     */
    public AdminDTO createAdmin(AdminDTO admin);

    /**
     * Update admin, that has already been persisted, in database.
     * 
     * @param admin admin to be updated
     * @return updated admin
     */
    public AdminDTO updateAdmin(AdminDTO admin);

    /**
     * Delete admin from database.
     * 
     * @param admin admin to be deleted
     * @return true if admin is deleted, false otherwise
     */
    public Boolean deleteAdmin(AdminDTO admin);

    /**
     * Retrieve list of all admins from database.
     * 
     * @return list of admins
     */
    public List<AdminDTO> retrieveAllAdmins();

    /**
     * Retrieve one admin with unique id.
     * 
     * @param id admin id
     * @return admin with given id or null if admin doesn't exist
     */
    public AdminDTO retrieveAdminById(long id);

    /**
     * Retrieve one admin with unique name.
     * @param name admin name
     * @return admin with given name or null if admin doesn't exist
     */
    public AdminDTO retrieveAdminByName(String name);
    
    /**
     * Delete all admins from database.
     * 
     * @return true if all admins were deleted, false otherwise
     */
    public Boolean deleteAllAdmins();
    
}
