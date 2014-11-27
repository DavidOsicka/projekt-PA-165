/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pa165.ddtroops.web;

import com.pa165.ddtroops.dto.AdminDTO;
import com.pa165.ddtroops.service.AdminService;
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
 *
 * @author Jakub Kovarik
 */

@UrlBinding("/admins/{$event}/{admin.id}")
public class AdminActionBean extends BaseActionBean{
    
    final static Logger log = LoggerFactory.getLogger(AdminActionBean.class);
    
    @SpringBean
    protected AdminService adminService;
    
    //this part shows list of admins
    private List<AdminDTO> admins;
    
    @DefaultHandler
    public Resolution list(){
        log.debug("list()");
        admins = adminService.retrieveAllAdmins();
        return new ForwardResolution("/admin/list.jsp");
    }
    
    public List<AdminDTO> getAdmins(){
        return admins;
    }
    
    //this part creates admin
    
    @ValidateNestedProperties(value= {
        @Validate(on = {"add", "save"}, field = "name", required = true)
    })
    private AdminDTO admin;
    
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
       
    public Resolution create(){
        log.debug("create()");
        return new ForwardResolution("/admin/create.jsp");
    }
    
    public Resolution add(){
        log.debug("add() admin={}",admin);
        adminService.createAdmin(admin);
        getContext().getMessages().add(new LocalizableMessage("admin.add.message",escapeHTML(admin.getName())));
        return new RedirectResolution(this.getClass(),"list");
    }
    
    public AdminDTO getAdmin(){
        return admin;
    }
    
    public void setAdmin(AdminDTO admin){
        this.admin = admin;
    }
    
    //this part deletes admin
    public Resolution delete(){
        log.debug("delete()", admin.getId());
        admin = adminService.retrieveAdminById(admin.getId());
        adminService.deleteAdmin(admin);
        getContext().getMessages().add(new LocalizableMessage("admin.delete.message", escapeHTML(admin.getName())));
        return new RedirectResolution(this.getClass(),"list");
    }
    
    //this part edit admin
     @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadAdminFromDatabase() {
        String ids = getContext().getRequest().getParameter("admin.id");
        if (ids == null) return;
        admin = adminService.retrieveAdminById(Long.parseLong(ids));
    }
    
    public Resolution edit() {
        log.debug("edit() admin={}", admin);
        return new ForwardResolution("/admin/edit.jsp");
    }
    
    public Resolution save() {
        log.debug("save() admin={}", admin);
        adminService.updateAdmin(admin);
        return new RedirectResolution(this.getClass(), "list");
    }
}
