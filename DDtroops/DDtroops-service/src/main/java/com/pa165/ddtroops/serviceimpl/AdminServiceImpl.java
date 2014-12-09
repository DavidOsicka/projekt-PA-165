/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.ddtroops.serviceimpl;

import com.pa165.ddtroops.dao.AdminDAO;
import com.pa165.ddtroops.api.dto.AdminDTO;
import com.pa165.ddtroops.entity.Admin;
import com.pa165.ddtroops.api.service.AdminService;
import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Martin Jelínek
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    
    @Autowired
    private Mapper mapper;
    
    @Autowired
    private AdminDAO adminDAO;
    
    public AdminServiceImpl() { }
    
    public AdminServiceImpl(AdminDAO adminDAO, Mapper mapper) {
        this.adminDAO = adminDAO;
        this.mapper = mapper;
    }
    
    private AdminDTO mapDTO(Admin admin) {
        return mapper == null || admin == null ? null : mapper.map(admin, AdminDTO.class);
    }
    
    private Admin mapEntity(AdminDTO adminDTO) {
        return mapper == null || adminDTO == null ? null : mapper.map(adminDTO, Admin.class);
    }

    @Override
    public Boolean deleteAllAdmins() {
        Boolean result = true;
        for (AdminDTO admin : retrieveAllAdmins()) {
            result &= deleteAdmin(admin);
        }
        return result;
    }

    // methods same as in DAO
    @Override
    public AdminDTO createAdmin(AdminDTO admin) {
        return mapDTO(adminDAO.createAdmin(
                mapEntity(admin)));
    }

    @Override
    public AdminDTO updateAdmin(AdminDTO admin) {
        return mapDTO(adminDAO.updateAdmin(
                mapEntity(admin)));
    }

    @Override
    public Boolean deleteAdmin(AdminDTO admin) {
        return adminDAO.deleteAdmin(
                mapEntity(admin));
    }

    @Override
    public List<AdminDTO> retrieveAllAdmins() {
        List<AdminDTO> admins = new ArrayList<>();
        for (Admin admin : adminDAO.retrieveAllAdmins()) {
            admins.add(mapDTO(admin));
        }
        return admins;
    }

    @Override
    public AdminDTO retrieveAdminById(long id) {
        return mapDTO(adminDAO.retrieveAdminById(id));
    }

    @Override
    public AdminDTO retrieveAdminByName(String name) {
        return mapDTO(adminDAO.retrieveAdminByName(name));
    }
    
}
