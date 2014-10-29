/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.ddtroops.serviceimpl;

import com.pa165.ddtroops.dao.AdminDAO;
import com.pa165.ddtroops.dto.AdminDTO;
import com.pa165.ddtroops.entity.Admin;
import com.pa165.ddtroops.service.AdminService;
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

    @Override
    public AdminDTO createAdmin(AdminDTO admin) {
        return mapper.map(adminDAO.createAdmin(mapper.map(admin, Admin.class)), AdminDTO.class);
    }
    
}
