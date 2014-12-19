/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pa165.ddtroops.web;

import com.pa165.ddtroops.api.dto.AdminDTO;
import com.pa165.ddtroops.api.service.AdminService;
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
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AdminActionBean handles actions in administrator web part
 * 
 * @version 1.0,10/12/2014
 * @author Jakub Kovarik
 */

@UrlBinding("/admins/{$event}/{admin.id}")
public class AdminActionBean extends BaseActionBean{
    
    final static Logger log = LoggerFactory.getLogger(AdminActionBean.class);
    @SpringBean
    protected AdminService adminService;
    private List<AdminDTO> admins;//this part shows list of admins
    
    @ValidateNestedProperties(value= {
        @Validate(on = {"add", "save"}, field = "name", required = true)
    })
    private AdminDTO admin;
    
    /**
     * Method switches to page with list of administrators
     * 
     * @return action
     */
    @DefaultHandler
    public Resolution list(){
        log.debug("list()");
        admins = adminService.retrieveAllAdmins();
        return new ForwardResolution("/admin/list.jsp");
    }
    
    /**
     * Method get current list of administrators
     * 
     * @return admins list of administrators
     */
    public List<AdminDTO> getAdmins(){
        return admins;
    }
    
    /**
     * Method for name validation
     * 
     * @param errors 
     */
    @ValidationMethod(on = "add")
    public void createUniqueName(ValidationErrors errors) {
        List<AdminDTO> adms = adminService.retrieveAllAdmins();
        if(adms.size() > 0) {
            for(AdminDTO a : adms) {
                if(admin.getName().equals(a.getName())) {
                    errors.add("admin.name", new LocalizableError("admin.save.samenameerror"));
                }
            }
        }
    }
    
    /**
     * Method for name and identifier validation
     * 
     * @param errors 
     */
    @ValidationMethod(on = "save")
    public void updateUniqueName(ValidationErrors errors) {
        List<AdminDTO> adms = adminService.retrieveAllAdmins();
        if(adms.size() > 0) {
            for(AdminDTO a : adms) {
                if((admin.getName().equals(a.getName())) && (admin.getId() != a.getId())) {
                    errors.add("admin.name", new LocalizableError("admin.save.samenameerror"));
                }
            }
        }
    }
    
    /**
     * Method switches to create page
     * 
     * @return action
     */
    public Resolution create(){
        log.debug("create()");
        return new ForwardResolution("/admin/create.jsp");
    }
    
    /**
     * Method creates administrator and switches to list page
     * 
     * @return action
     */
    public Resolution add(){
        log.debug("add() admin={}",admin);
        adminService.createAdmin(admin);
        getContext().getMessages().add(new LocalizableMessage("admin.add.message",escapeHTML(admin.getName())));
        return new RedirectResolution(this.getClass(),"list");
    }
    
    /**
     * Gets current administrator
     * 
     * @return current administrator
     */
    public AdminDTO getAdmin(){
        return admin;
    }
    
    /**
     * Sets current administrator
     * 
     * @param admin administrator
     */
    public void setAdmin(AdminDTO admin){
        this.admin = admin;
    }
    
    /**
     * Method deletes administrator and switches to list page
     * 
     * @return action
     */
    public Resolution delete(){
        log.debug("delete()", admin.getId());
        admin = adminService.retrieveAdminById(admin.getId());
        adminService.deleteAdmin(admin);
        getContext().getMessages().add(new LocalizableMessage("admin.delete.message", escapeHTML(admin.getName())));
        return new RedirectResolution(this.getClass(),"list");
    }
    
    /**
     * Method retrieves administrator according to identifier
     */
     @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadAdminFromDatabase() {
        String ids = getContext().getRequest().getParameter("admin.id");
        if (ids == null) return;
        admin = adminService.retrieveAdminById(Long.parseLong(ids));
    }
    
    /**
     * Method switches to edit page
     * 
     * @return action
     */
    public Resolution edit() {
        log.debug("edit() admin={}", admin);
        return new ForwardResolution("/admin/edit.jsp");
    }
    
    /**
     * Method updates administrator and switches to list page
     * 
     * @return action
     */
    public Resolution save() {
        log.debug("save() admin={}", admin);
        adminService.updateAdmin(admin);
        return new RedirectResolution(this.getClass(), "list");
    }
}
