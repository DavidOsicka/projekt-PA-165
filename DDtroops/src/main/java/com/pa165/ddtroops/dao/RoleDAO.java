package com.pa165.ddtroops.dao;


import com.pa165.ddtroops.entity.Role;
import java.util.List;


public interface RoleDAO {

    public Role createRole (Role role);

    public Role updateRole (Role role);

    public Boolean deleteRole (Role role);

    public List<Role> retrieveAllRoles ();

    public Role retrieveRoleById (long id);

    public Role retrieveRoleByName (String name);

}

