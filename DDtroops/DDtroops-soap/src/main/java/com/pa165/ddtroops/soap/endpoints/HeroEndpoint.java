package com.pa165.ddtroops.soap.endpoints;

import com.pa165.ddtroops.api.dto.HeroDTO;
import com.pa165.ddtroops.api.service.HeroService;
import com.pa165.ddtroops.soap.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.pa165.ddtroops.soap.GetHeroByNameRequest;
import com.pa165.ddtroops.soap.GetHeroByNameResponse;
import com.pa165.ddtroops.soap.exceptions.HeroNotFoundException;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;

/**
 * We define an endpoint by means of the Endpoint annotation an endpoint can
 * have several handler methods for different uris/operations
 *
 */
@Endpoint
public class HeroEndpoint {

    private static final String NAMESPACE_URI = "http://pa165.com/ddtroops/soap";

    private final HeroService heroService;
    
    @Autowired
    private Mapper mapper;

    /**
     * We autowire the HeroRepository service
     *
     */
    @Autowired
    public HeroEndpoint(HeroService heroService) {
        this.heroService = heroService;
    }

    /**
     *
     * Payload root defined an handler methods, by using the general base
     * namespace uri and the specific part for this handler we mark to return
     * the ResponsePayload and we annotate the variable for the RequestPayload
     */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getHeroByNameRequest")
    @ResponsePayload
    public GetHeroByNameResponse getHero(@RequestPayload GetHeroByNameRequest request) {

        GetHeroByNameResponse response = new GetHeroByNameResponse();
        HeroDTO heroDTO = heroService.retrieveHeroByName(request.getName());
        if (heroDTO == null) {
            throw new HeroNotFoundException(request.getName());
        }
        
        Hero hero = mapper.map(heroDTO, Hero.class);
        response.getHero().add(hero);
        return response;

    }
}
