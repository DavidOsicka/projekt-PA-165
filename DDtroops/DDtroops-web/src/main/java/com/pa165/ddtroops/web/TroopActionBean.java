/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.ddtroops.web;

import com.pa165.ddtroops.dto.TroopDTO;
import com.pa165.ddtroops.service.HeroService;
import com.pa165.ddtroops.service.TroopService;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Stripes ActionBean for handling troop operations.
 * 
 * @author Jakub Szotkowski
 */
@UrlBinding("/troops/{$event}/{troop.id}")
public class TroopActionBean extends BaseActionBean {
    
    final static Logger log = LoggerFactory.getLogger(TroopActionBean.class);

    @SpringBean
    protected HeroService heroService;
    
    @SpringBean
    protected TroopService troopService;

    //this part show list of troops
    private List<TroopDTO> troops;

    @DefaultHandler
    public Resolution list() {
        log.debug("list()");
        troops = troopService.retrieveAllTroops();
        return new ForwardResolution("/troop/list.jsp");
    }

    public List<TroopDTO> getTroops() {
        return troops;
    }

    //part for create troop

    @ValidateNestedProperties(value = {
            @Validate(on = {"add", "save"}, field = "name", required = true),
            @Validate(on = {"add", "save"}, field = "mission", required = true),
            @Validate(on = {"add", "save"}, field = "amountOfGM", required = true, minvalue = 0)
    })
    private TroopDTO troop;

    public Resolution create() {
        log.debug("create()");
        return new ForwardResolution("/troop/create.jsp");
    }

    public Resolution add() {
        log.debug("add() troop={}", troop);
        troopService.createTroop(troop);
        getContext().getMessages().add(new LocalizableMessage("troop.add.message",escapeHTML(troop.getName()),escapeHTML(troop.getMission())));
        return new RedirectResolution(this.getClass(), "list");
    }

    public TroopDTO getTroop() {
        return troop;
    }

    public void setTroop(TroopDTO troop) {
        this.troop = troop;
    }

    //part for delete troop

    public Resolution delete() {
        log.debug("delete()", troop.getId());
        //only id is filled by the form
        troop = troopService.retrieveTroopById(troop.getId());
        troopService.deleteTroop(troop);
        getContext().getMessages().add(new LocalizableMessage("troop.delete.message", escapeHTML(troop.getName()),escapeHTML(troop.getMission())));
        return new RedirectResolution(this.getClass(), "list");
    }

    //part for edit troop

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadHeroFromDatabase() { //preklep? 
        String ids = getContext().getRequest().getParameter("troop.id");
        if (ids == null) return;
        troop = troopService.retrieveTroopById(Long.parseLong(ids));
    }

    public Resolution edit() {
        log.debug("edit() troop={}", troop);
        return new ForwardResolution("/troop/edit.jsp");
    }

    public Resolution save() {
        log.debug("save() troop={}", troop);
        troopService.updateTroop(troop);
        return new RedirectResolution(this.getClass(), "list");
    }
    
}
