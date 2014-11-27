package com.pa165.ddtroops.web;

import com.pa165.ddtroops.dto.HeroDTO;
import com.pa165.ddtroops.dto.RoleDTO;
import com.pa165.ddtroops.dto.TroopDTO;
import com.pa165.ddtroops.service.HeroService;
import com.pa165.ddtroops.service.RoleService;
import com.pa165.ddtroops.service.TroopService;
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
    
    public List<RoleDTO> getAllRoles() {
        return roleService.retrieveAllRoles();
    }
    
    public List<TroopDTO> getAllTroops() {
        return troopService.retrieveAllTroops();
    }
    
    private List<Long> newRoles;

    public List<Long> getNewRoles() {
        return newRoles;
    }

    public void setNewRoles(List<Long> newRoles) {
        this.newRoles = newRoles;
    }
    
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

    //--- part for showing a list of heroes ----
    private List<HeroDTO> heroes;

    @DefaultHandler
    public Resolution list() {
        log.debug("list()");
        heroes = heroService.retrieveAllHeroes();
        return new ForwardResolution("/hero/list.jsp");
    }

    public List<HeroDTO> getHeroes() {
        return heroes;
    }

    //--- part for adding a hero ----

    @ValidateNestedProperties(value = {
            @Validate(on = {"add", "save"}, field = "name", required = true),
            @Validate(on = {"add", "save"}, field = "race", required = true),
            @Validate(on = {"add", "save"}, field = "xp", required = true, minvalue = 0)
    })
    
    @ValidationMethod(on = "add")
    public void createUniqueName(ValidationErrors errors) {
        List<HeroDTO> hrs = heroService.retrieveAllHeroes();
        if(hrs.size() > 0) {
            for(HeroDTO h : hrs) {
                if(hero.getName().equals(h.getName())) {
                    errors.add("name", new LocalizableError("hero.save.samenameerror"));
                }
            }
        }
    }
    
    @ValidationMethod(on = "save")
    public void updateUniqueName(ValidationErrors errors) {
        List<HeroDTO> hrs = heroService.retrieveAllHeroes();
        if(hrs.size() > 0) {
            for(HeroDTO h : hrs) {
                if((hero.getName().equals(h.getName())) && (hero.getId() != h.getId())) {
                    errors.add("name", new LocalizableError("hero.save.samenameerror"));
                }
            }
        }
    }
    
    private HeroDTO hero;
    
    @ValidationMethod(on = "add")
    public void createUniqueName(ValidationErrors errors) {
        HeroDTO existingHero = heroService.retrieveHeroByName(hero.getName());
        if (existingHero != null && hero.getName().equals(existingHero.getName())) {
            errors.add("hero.name", new LocalizableError("hero.save.samenameerror"));
        }
    }
    
    @ValidationMethod(on = "save")
    public void updateUniqueName(ValidationErrors errors) {
        HeroDTO existingHero = heroService.retrieveHeroByName(hero.getName());
        if (existingHero != null && hero.getName().equals(existingHero.getName()) &&
                hero.getId() != existingHero.getId()) {
            errors.add("hero.name", new LocalizableError("hero.save.samenameerror"));
        }
    }

    public Resolution create() {
        log.debug("create()");
        return new ForwardResolution("/hero/create.jsp");
    }

    public Resolution add() {
        log.debug("add() hero={}", hero);
        fillNewRoles();
        heroService.createHero(hero);
        getContext().getMessages().add(new LocalizableMessage("hero.add.message",escapeHTML(hero.getName()),escapeHTML(hero.getRace())));
        return new RedirectResolution(this.getClass(), "list");
    }

    public HeroDTO getHero() {
        return hero;
    }

    public void setHero(HeroDTO hero) {
        this.hero = hero;
    }

    //--- part for deleting a hero ----

    public Resolution delete() {
        log.debug("delete()", hero.getId());
        //only id is filled by the form
        hero = heroService.retrieveHeroById(hero.getId());
        heroService.deleteHero(hero);
        getContext().getMessages().add(new LocalizableMessage("hero.delete.message", escapeHTML(hero.getName()),escapeHTML(hero.getRace())));
        return new RedirectResolution(this.getClass(), "list");
    }

    //--- part for editing a hero ----

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadHeroFromDatabase() {
        String ids = getContext().getRequest().getParameter("hero.id");
        if (ids == null) return;
        hero = heroService.retrieveHeroById(Long.parseLong(ids));
    }

    public Resolution edit() {
        log.debug("edit() hero={}", hero);
        newRoles = new ArrayList<Long>();
        for (RoleDTO role : hero.getRole()) {
            newRoles.add(role.getId());
        }
        return new ForwardResolution("/hero/edit.jsp");
    }

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
