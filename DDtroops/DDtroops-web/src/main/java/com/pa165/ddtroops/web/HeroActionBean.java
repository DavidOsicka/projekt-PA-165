package com.pa165.ddtroops.web;

import com.pa165.ddtroops.api.dto.HeroDTO;
import com.pa165.ddtroops.api.dto.RoleDTO;
import com.pa165.ddtroops.api.dto.TroopDTO;
import com.pa165.ddtroops.api.service.HeroService;
import com.pa165.ddtroops.api.service.RoleService;
import com.pa165.ddtroops.api.service.TroopService;
import java.util.ArrayList;
import java.util.HashSet;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

/**
 * Stripes ActionBean for handling hero operations.
 *
 * @version 1.0,10/12/2014
 * @author Martin Jelínek
 */
@UrlBinding("/heroes/{$event}/{hero.id}")
public class HeroActionBean extends BaseActionBean {

    final static Logger log = LoggerFactory.getLogger(HeroActionBean.class);
    
    @SpringBean
    protected HeroService heroService;
    @SpringBean
    protected RoleService roleService;
    @SpringBean
    protected TroopService troopService;
    
    private List<Long> newRoles;
    private List<HeroDTO> heroes;
    
    @ValidateNestedProperties(value = {
            @Validate(on = {"add", "save"}, field = "name", required = true),
            @Validate(on = {"add", "save"}, field = "race", required = true),
            @Validate(on = {"add", "save"}, field = "xp", required = true, minvalue = 0)
    })
    private HeroDTO hero;
    
    /**
     * Method gets all roles
     * 
     * @return roles
     */
    public List<RoleDTO> getAllRoles() {
        return roleService.retrieveAllRoles();
    }
    
    /**
     * Method gets all troops
     * 
     * @return troops
     */
    public List<TroopDTO> getAllTroops() {
        return troopService.retrieveAllTroops();
    }
    
    
    /**
     * Gets new roles
     * 
     * @return newRoles
     */
    public List<Long> getNewRoles() {
        return newRoles;
    }

    /**
     * Sets new roles
     * 
     * @param newRoles new roles
     */
    public void setNewRoles(List<Long> newRoles) {
        this.newRoles = newRoles;
    }
    
    /**
     * Method fills a set with new roles
     */
    private void fillNewRoles() {
        HashSet<RoleDTO> newRolesSet = new HashSet<RoleDTO>();
        if (hero != null && newRoles != null) {
            for (Long newRoleId : newRoles) {
                RoleDTO newRole = new RoleDTO();
                newRole.setId(newRoleId);
                newRolesSet.add(newRole);
            }
        }
        hero.setRole(newRolesSet);
    }

    /**
     * Method switches to list page
     * 
     * @return action
     */
    @DefaultHandler
    public Resolution list() {
        log.debug("list()");
        heroes = heroService.retrieveAllHeroes();
        return new ForwardResolution("/hero/list.jsp");
    }

    /**
     * Method gets heroes
     * 
     * @return heroes
     */
    public List<HeroDTO> getHeroes() {
        return heroes;
    }

    /**
     * Method validates name
     * 
     * @param errors 
     */
    @ValidationMethod(on = "add")
    public void createUniqueName(ValidationErrors errors) {
        HeroDTO existingHero = heroService.retrieveHeroByName(hero.getName());
        if (existingHero != null && hero.getName().equals(existingHero.getName())) {
            errors.add("hero.name", new LocalizableError("hero.save.samenameerror"));
        }
    }
    
    /**
     * Method validates name and identifier
     * 
     * @param errors 
     */
    @ValidationMethod(on = "save")
    public void updateUniqueName(ValidationErrors errors) {
        HeroDTO existingHero = heroService.retrieveHeroByName(hero.getName());
        if (existingHero != null && hero.getName().equals(existingHero.getName()) &&
                hero.getId() != existingHero.getId()) {
            errors.add("hero.name", new LocalizableError("hero.save.samenameerror"));
        }
    }
    
    /**
     * Method switches to create page
     * 
     * @return action
     */
    public Resolution create() {
        log.debug("create()");
        return new ForwardResolution("/hero/create.jsp");
    }
    
    /**
     * Method creates hero and switches to list page
     * 
     * @return action
     */
    public Resolution add() {
        log.debug("add() hero={}", hero);
        fillNewRoles();
        heroService.createHero(hero);
        getContext().getMessages().add(new LocalizableMessage("hero.add.message",escapeHTML(hero.getName()),escapeHTML(hero.getRace())));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    /**
     * Method gets hero
     * 
     * @return hero
     */
    public HeroDTO getHero() {
        return hero;
    }
    
    /**
     * Method sets hero
     * 
     * @param hero hero
     */
    public void setHero(HeroDTO hero) {
        this.hero = hero;
    }

    /**
     * Method deletes hero and switches to list page
     * 
     * @return action
     */
    public Resolution delete() {
        log.debug("delete()", hero.getId());
        //only id is filled by the form
        hero = heroService.retrieveHeroById(hero.getId());
        heroService.deleteHero(hero);
        getContext().getMessages().add(new LocalizableMessage("hero.delete.message", escapeHTML(hero.getName()),escapeHTML(hero.getRace())));
        return new RedirectResolution(this.getClass(), "list");
    }

    /**
     * Method loads hero from database 
     */
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadHeroFromDatabase() {
        String ids = getContext().getRequest().getParameter("hero.id");
        if (ids == null) return;
        hero = heroService.retrieveHeroById(Long.parseLong(ids));
    }

    /**
     * Method switches to  edit page
     * 
     * @return action
     */
    public Resolution edit() {
        log.debug("edit() hero={}", hero);
        newRoles = new ArrayList<Long>();
        for (RoleDTO role : hero.getRole()) {
            newRoles.add(role.getId());
        }
        return new ForwardResolution("/hero/edit.jsp");
    }

    /**
     * Method updates hero and switches to list page
     * 
     * @return action
     */
    public Resolution save() {
        log.debug("save() hero={}", hero);
        if (hero.getTroop() != null && hero.getTroop().getId() == 0) {
            hero.setTroop(null);
        }
        fillNewRoles();
        heroService.updateHero(hero);
        return new RedirectResolution(this.getClass(), "list");
    }
}
