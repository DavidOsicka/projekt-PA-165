package com.pa165.ddtroops.dao;


import com.pa165.ddtroops.entity.Admin;
import java.util.List;


public interface AdminDAO {

    public Admin createAdmin (Admin admin);

    public Admin updateAdmin (Admin admin);

    public Boolean deleteAdmin (Admin admin);

    public List<Admin> retrieveAllAdmins ();

    public Admin retrieveAdminById (long id);

    public Admin retrieveAdminByName (String name);

}

