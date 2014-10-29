package com.pa165.ddtroops.dao;


import com.pa165.ddtroops.entity.Admin;
import java.util.List;

/**
 * @author Jakub Kovařík 
 * 
 * Interface for D&D admin dao
 */
public interface AdminDAO {

    /**
     * Insert new Admin in database. 
     * @param admin admin to be inserted in database
     * @return inserted admin
     */
    public Admin createAdmin (Admin admin);

    /**
     * Update admin in database.
     * @param admin admin to be updated
     * @return updated admin
     */
    public Admin updateAdmin (Admin admin);

    /**
     * Delete admin from database.
     * @param admin admin to be deleted
     * @return true if admin is deleted, false otherwise
     */
    public Boolean deleteAdmin (Admin admin);
    
    /**
     * Retrieve all admins from database.
     * @return list of all persisted admins
     */
    public List<Admin> retrieveAllAdmins ();

    /**
     * Retrive one admin from database with specified id.
     * @param id id of searched admin
     * @return admin with given id, or null if admin doesn't exist
     */
    public Admin retrieveAdminById (long id);

    /**
     * Retrieve one admin from database with specified name.
     * @param name name of searched admin
     * @return admin with given name, or null if admin dosen't exist
     */
    public Admin retrieveAdminByName (String name);

}

