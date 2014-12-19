/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pa165.ddtroops.web;

import com.pa165.ddtroops.api.dto.HeroDTO;
import com.pa165.ddtroops.api.dto.RoleDTO;
import com.pa165.ddtroops.api.service.HeroService;
import com.pa165.ddtroops.api.service.RoleService;
import static com.pa165.ddtroops.web.BaseActionBean.escapeHTML;
import java.util.List;
import java.util.Set;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Stripes ActionBean for handling role operations. 
 * 
 * @version 1.0,10/12/2014
 * @author Martin Peska
 */
@UrlBinding("/roles/{$event}/{role.id}")
public class RoleActionBean extends BaseActionBean{
    final static Logger log = LoggerFactory.getLogger(RoleActionBean.class);
    
    @SpringBean
    protected RoleService roleService;
    @SpringBean
    protected HeroService heroService;
    
    @ValidateNestedProperties(value = {
            @Validate(on = {"add", "save"}, field = "name", required = true),
            @Validate(on = {"add", "save"}, field = "description", required = true),
            @Validate(on = {"add", "save"}, field = "energy", required = true, minvalue = 0),
            @Validate(on = {"add", "save"}, field = "attack", required = true, minvalue = 0),
            @Validate(on = {"add", "save"}, field = "defense", required = true, minvalue = 0)
    })
    private RoleDTO role;
    
    private List<RoleDTO> roles;    // part with displaying of roles
    
    /**
     * Method switches to list page
     * 
     * @return action 
     */
    @DefaultHandler
    public Resolution list() {
        log.debug("list()");
        roles = roleService.retrieveAllRoles();
        return new ForwardResolution("/role/list.jsp");
    }

    /**
     * Method gets roles
     * 
     * @return roles
     */
    public List<RoleDTO> getRoles() {
        return roles;
    }

    /**
     * Method validates role name
     * 
     * @param errors 
     */
    @ValidationMethod(on = "add")
    public void createUniqueName(ValidationErrors errors) {
        List<RoleDTO> rls = roleService.retrieveAllRoles();
        if(rls.size() > 0) {
            for(RoleDTO r : rls) {
                if(role.getName().equals(r.getName())) {
                    errors.add("role.name", new LocalizableError("role.save.samenameerror"));
                }
            }
        }
    }
    
    /**
     * Method validates role name and identifier
     * @param errors 
     */
    @ValidationMethod(on = "save")
    public void updateUniqueName(ValidationErrors errors) {
        List<RoleDTO> rls = roleService.retrieveAllRoles();
        if(rls.size() > 0) {
            for(RoleDTO r : rls) {
                if((role.getName().equals(r.getName())) && (role.getId() != r.getId())) {
                    errors.add("role.name", new LocalizableError("role.save.samenameerror"));
                }
            }
        }
    }
    
    /**
     * Method switches to create role page
     * 
     * @return action
     */
    public Resolution create() {
        log.debug("create()");
        return new ForwardResolution("/role/create.jsp");
    }

    /**
     * Method creates role and switches to list page
     * 
     * @return action
     */
    public Resolution add() {
        log.debug("add() role={}", role);
 
        roleService.createRole(role);
        getContext().getMessages().add(new LocalizableMessage("role.add.message",escapeHTML(role.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }

    /**
     * Method gets role
     * 
     * @return role
     */
    public RoleDTO getRole() {
        return role;
    }

    /**
     * Method sets role
     * 
     * @param role 
     */
    public void setRole(RoleDTO role) {
        this.role = role;
    }

    /**
     * Method deletes role and switches to list page
     * 
     * @return action
     */
    public Resolution delete() {
        log.debug("delete()", role.getId());
        //only id is filled by the form
        role = roleService.retrieveRoleById(role.getId());
        Set<HeroDTO> heroes = role.getHeroes();
        if(heroes != null) {
            if(heroes.size() > 0) {
                for(HeroDTO h : heroes) {
                    h.getRole().remove(role);
                    heroService.updateHero(h);
                }
            }
        }
        roleService.deleteRole(role);
        getContext().getMessages().add(new LocalizableMessage("role.delete.message", escapeHTML(role.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }

    /**
     * Method loads role from database according to identifier
     */
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadRoleFromDatabase() {
        String ids = getContext().getRequest().getParameter("role.id");
        if (ids == null) return;
        role = roleService.retrieveRoleById(Long.parseLong(ids));
    }

    /**
     * Method switches to edit page
     * 
     * @return action
     */
    public Resolution edit() {
        log.debug("edit() role={}", role);
        return new ForwardResolution("/role/edit.jsp");
    }
    
    /**
     * Method updates role and switches to list page
     * 
     * @return action
     */
    public Resolution save() {
        log.debug("save() role={}", role);
        roleService.updateRole(role);
        return new RedirectResolution(this.getClass(), "list");
    }
}
