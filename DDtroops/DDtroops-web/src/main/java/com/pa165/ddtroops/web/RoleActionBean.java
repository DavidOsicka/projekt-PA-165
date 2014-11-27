/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pa165.ddtroops.web;

import com.pa165.ddtroops.dto.RoleDTO;
import com.pa165.ddtroops.service.RoleService;
import static com.pa165.ddtroops.web.BaseActionBean.escapeHTML;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Martin Peska
 */
@UrlBinding("/roles/{$event}/{role.id}")
public class RoleActionBean extends BaseActionBean{
     final static Logger log = LoggerFactory.getLogger(RoleActionBean.class);
    
    @SpringBean
    protected RoleService roleService;
    
    // part with displaying of roles
    private List<RoleDTO> roles;
    
    @DefaultHandler
    public Resolution list() {
        log.debug("list()");
        roles = roleService.retrieveAllRoles();
        return new ForwardResolution("/role/list.jsp");
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    // adding a role

    @ValidateNestedProperties(value = {
            @Validate(on = {"add", "save"}, field = "name", required = true),
            @Validate(on = {"add", "save"}, field = "description", required = true),
            @Validate(on = {"add", "save"}, field = "energy", required = true, minvalue = 0),
            @Validate(on = {"add", "save"}, field = "attack", required = true, minvalue = 0),
            @Validate(on = {"add", "save"}, field = "defense", required = true, minvalue = 0)
    })
    private RoleDTO role;

    public Resolution create() {
        log.debug("create()");
        return new ForwardResolution("/role/create.jsp");
    }

    public Resolution add() {
        log.debug("add() role={}", role);
 
        roleService.createRole(role);
        getContext().getMessages().add(new LocalizableMessage("role.add.message",escapeHTML(role.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    //--- part for deleting a role ----

    public Resolution delete() {
        log.debug("delete()", role.getId());
        //only id is filled by the form
        role = roleService.retrieveRoleById(role.getId());
        roleService.deleteRole(role);
        getContext().getMessages().add(new LocalizableMessage("role.delete.message", escapeHTML(role.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }

    //--- part for editing a role ----

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadRoleFromDatabase() {
        String ids = getContext().getRequest().getParameter("role.id");
        if (ids == null) return;
        role = roleService.retrieveRoleById(Long.parseLong(ids));
    }

    public Resolution edit() {
        log.debug("edit() role={}", role);
        return new ForwardResolution("/role/edit.jsp");
    }

    public Resolution save() {
        log.debug("save() role={}", role);
        roleService.updateRole(role);
        return new RedirectResolution(this.getClass(), "list");
    }
}
