package com.pa165.ddtroops.dao;


import com.pa165.ddtroops.entity.Hero;
import java.util.List;


public interface HeroDAO {

    public Hero createHero (Hero hero);

    public Hero updateHero (Hero hero);

    public Boolean deleteHero (Hero hero);

    public List<Hero> retrieveAllHeroes ();

    public Hero retrieveHeroById (long id);

    public Hero retrieveHeroByName (String name);

}

