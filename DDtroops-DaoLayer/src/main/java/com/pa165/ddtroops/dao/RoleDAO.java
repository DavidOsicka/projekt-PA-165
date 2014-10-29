package com.pa165.ddtroops.dao;


import com.pa165.ddtroops.entity.Role;
import java.util.List;

/**
 * @author Martin Peška
 * 
 * Interface RoleDAO offers methods for CRUD operations
 */
public interface RoleDAO {

    /**
     * Method createRole creates role in database and returns it
     * 
     * Method creates instance of EntityManager, begins transaction and 
     * saves object role to database, then transaction is committed. If there
     * is thrown an exception, the transaction will be restored. Finally 
     * EntityManager instance is closed.
     * 
     * @param role role which is being saved
     * @return     role 
     */
    public Role createRole (Role role);
    
    /**
     * Method updates attributes of role in database and returns it
     * 
     * Method creates instance of EntityManager, begins transaction and updates
     * attributes of the role object in database, then transaction is committed.
     * If there is thrown an exception, the transaction will be restored. Finally
     * EntityManager instance is closed.
     * 
     * @param role role which is being updated
     * @return     role
     */
    public Role updateRole (Role role);

    /**
     * Method deletes role from database and returns it
     * 
     * Method creates instance of EntityManager, finds the role by id, then
     * begins transaction and deletes found role, then transaction is committed.
     * If there is thrown an exception, the transaction will be restored. Finally
     * EntityManager instance is closed.
     * 
     * @param role role which is being deleted
     * @return     role
     */
    public Boolean deleteRole (Role role);

    /**
     * Method returns all roles saved in database
     * 
     * Method creates instance of EntityManager, then creates SQL query which returns
     * list of all saved roles in database. Finally EntityManager instance is closed
     * and the list is returned.
     * 
     * @return     list of all saved roles 
     */
    public List<Role> retrieveAllRoles ();

    /**
     * Method returns role found by id
     * 
     * Method creates instance of EntityManager, then calls find method which 
     * belongs to EntityManager and finds role by id. Finally Entity manager instance
     * is closed and the found role is returned.
     * 
     * @param id id which is used to find the role
     * @return     found role
     */
    public Role retrieveRoleById (long id);
    
    /**
     * Method returns role found by unique name
     * 
     * Method creates instance of EntityManager, then creates SQL query 
     * which returns single role found by name. Finally Entity manager instance
     * is closed and the found role is returned.
     * 
     * @param name name which is used to find the role
     * @return     found role
     */
    public Role retrieveRoleByName (String name);

}

