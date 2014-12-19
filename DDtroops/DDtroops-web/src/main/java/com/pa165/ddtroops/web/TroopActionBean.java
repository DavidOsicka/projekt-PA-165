/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.ddtroops.web;

import com.pa165.ddtroops.api.dto.HeroDTO;
import com.pa165.ddtroops.api.dto.TroopDTO;
import com.pa165.ddtroops.api.service.HeroService;
import com.pa165.ddtroops.api.service.TroopService;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

/**
 * Stripes ActionBean for handling troop operations.
 * 
 * @version 1.0,10/12/2014
 * @author Jakub Szotkowski
 */
@UrlBinding("/troops/{$event}/{troop.id}")
public class TroopActionBean extends BaseActionBean {
    
    final static Logger log = LoggerFactory.getLogger(TroopActionBean.class);

    @SpringBean
    protected HeroService heroService;  
    @SpringBean
    protected TroopService troopService;
    
    private List<TroopDTO> troops;
    
    @ValidateNestedProperties(value = {
            @Validate(on = {"add", "save"}, field = "name", required = true),
            @Validate(on = {"add", "save"}, field = "mission", required = true),
            @Validate(on = {"add", "save"}, field = "amountOfGM", required = true, minvalue = 0)
    })
    private TroopDTO troop;

    /**
     * Method switches to list page
     * 
     * @return action
     */
    @DefaultHandler
    public Resolution list() {
        log.debug("list()");
        troops = troopService.retrieveAllTroops();
        return new ForwardResolution("/troop/list.jsp");
    }
    
    /**
     * Method gets troops
     * 
     * @return troops
     */
    public List<TroopDTO> getTroops() {
        return troops;
    }

    /**
     * Method validates troop name
     * 
     * @param errors 
     */
    @ValidationMethod(on = "add")
    public void createUniqueName(ValidationErrors errors) {
        List<TroopDTO> trps = troopService.retrieveAllTroops();
        if(trps.size() > 0) {
            for(TroopDTO t : trps) {
                if(troop.getName().equals(t.getName())) {
                    errors.add("troop.name", new LocalizableError("troop.save.samenameerror"));
                }
            }
        }    
    }
    
    /**
     * Method validates troop name and identifier
     * 
     * @param errors 
     */
    @ValidationMethod(on = "save")
    public void updateUniqueName(ValidationErrors errors) {
                List<TroopDTO> trps = troopService.retrieveAllTroops();
                if(trps.size() > 0) {
                    for(TroopDTO t : trps) {
                        if((troop.getName().equals(t.getName())) && (troop.getId() != t.getId())) {
                            errors.add("troop.name", new LocalizableError("troop.save.samenameerror"));
                        }
                    }
                }    
    }
    
    /**
     * Method switches to create page
     * 
     * @return action
     */
    public Resolution create() {
        log.debug("create()");
        return new ForwardResolution("/troop/create.jsp");
    }

    /**
     * Method creates troop and switches to list page
     * 
     * @return action
     */
    public Resolution add() {
        log.debug("add() troop={}", troop);
        troopService.createTroop(troop);
        getContext().getMessages().add(new LocalizableMessage("troop.add.message",escapeHTML(troop.getName()),escapeHTML(troop.getMission())));
        return new RedirectResolution(this.getClass(), "list");
    }

    /**
     * Method gets troop
     * 
     * @return troop
     */
    public TroopDTO getTroop() {
        return troop;
    }

    /**
     * Method sets troop
     * 
     * @param troop 
     */
    public void setTroop(TroopDTO troop) {
        this.troop = troop;
    }

    /**
     * Method deletes troop and switches to list page
     * 
     * @return action
     */
    public Resolution delete() {
        log.debug("delete()", troop.getId());
        //only id is filled by the form
        troop = troopService.retrieveTroopById(troop.getId());
        Set<HeroDTO> heroes = troop.getHeroes();
        if(heroes != null) {
            if(heroes.size() > 0) {
                for(HeroDTO h : heroes) {
                    h.setTroop(null);
                    heroService.updateHero(h);
                }
            }
        }
        troopService.deleteTroop(troop);
        getContext().getMessages().add(new LocalizableMessage("troop.delete.message", escapeHTML(troop.getName()),escapeHTML(troop.getMission())));
        return new RedirectResolution(this.getClass(), "list");
    }

    /**
     * Method loads troop from database according to identifier
     */
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadTroopFromDatabase() {
        String ids = getContext().getRequest().getParameter("troop.id");
        if (ids == null) return;
        troop = troopService.retrieveTroopById(Long.parseLong(ids));
    }

    /**
     * Method switches to edit page
     * 
     * @return action
     */
    public Resolution edit() {
        log.debug("edit() troop={}", troop);
        return new ForwardResolution("/troop/edit.jsp");
    }

    /**
     * Method updates troop and switches to list page
     * 
     * @return action
     */
    public Resolution save() {
        log.debug("save() troop={}", troop);
        troopService.updateTroop(troop);
        return new RedirectResolution(this.getClass(), "list");
    }
    
}
