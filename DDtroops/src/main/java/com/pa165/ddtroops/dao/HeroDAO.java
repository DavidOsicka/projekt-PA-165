package com.pa165.ddtroops.dao;


import com.pa165.ddtroops.entity.Hero;
import java.util.List;

/**
 *
 * @author Jakub Szotkowski
 * 
 * Interface for hero DAO with methods for crud operations.
 */
public interface HeroDAO {
    /**
     * Creates new hero in database and return him.
     * 
     * @param hero  Hero, what we want save to database without id
     * @return  hero with id from database
     */
    public Hero createHero (Hero hero);
    
    /**
     * Update hero in database and return updated hero.
     * @param hero  hero need to update
     * @return  updated hero
     */
    public Hero updateHero (Hero hero);

    /**
     * Delete hero from database.
     * @param hero hero, what we want delete from database
     * @return  true if hero is deleted, false otherwise
     */
    public Boolean deleteHero (Hero hero);

    /**
     * Retrieve list of all heroes from database.
     * @return  list of heroes
     */
    public List<Hero> retrieveAllHeroes ();

    /**
     * Retrieve one hero with unique id.
     * @param id    hero id
     * @return  hero with choosen id
     */
    public Hero retrieveHeroById (long id);

    /**
     * Retrieve one hero with unique name.
     * @param name  hero name
     * @return  hero with choosen name
     */
    public Hero retrieveHeroByName (String name);

}

