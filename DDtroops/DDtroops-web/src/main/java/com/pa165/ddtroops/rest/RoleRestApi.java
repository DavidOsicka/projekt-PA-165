/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.ddtroops.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pa165.ddtroops.api.dto.HeroDTO;
import com.pa165.ddtroops.api.dto.RoleDTO;
import com.pa165.ddtroops.api.service.RoleService;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.exception.DataException;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * REST Web Service
 * 
 * @version 1.0,19/12/2014
 * @author Jakub Szotkowski
 */
@Path("/role")
public class RoleRestApi {

    private static final XmlWebApplicationContext APP_CONF =
            new XmlWebApplicationContext();
    
    @Context
    private ServletContext context;
    private RoleService roleService; // role services used from api
    private ObjectMapper mapper = new ObjectMapper();
    
    static Authentication authentication = 
            new UsernamePasswordAuthenticationToken("rest", "rest", 
                Arrays.asList(new SimpleGrantedAuthority[] 
                    {new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER")}));
    
    /*
        Constructor
    */
    public RoleRestApi() {
        APP_CONF.setNamespace("applicationContext-web");
    }
    
    /**
     * Method initializes context and role services
     */
    private void initBeforeRequest() {
        APP_CONF.setServletContext(context);
        APP_CONF.refresh();
        roleService = APP_CONF.getBean(RoleService.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    
    /**
     * Destroy context after request
     */
    private void destroyAfterRequest(){
        SecurityContextHolder.getContext().setAuthentication(null);
    }
    
     /**
     * Method resets heroes in role
     * 
     * @param r role
     */
    private void resetRoleHeroes(RoleDTO r) {
        r.setHeroes(new HashSet<HeroDTO>());
    }
    
    /**
     * Method gets all roles with REST using JSON
     * 
     * @return JSON string containing list of roles
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllRoles() {
        try{
            initBeforeRequest();
            List<RoleDTO> roleList = roleService.retrieveAllRoles();
            for(RoleDTO r : roleList) {
                resetRoleHeroes(r);
            }
            return mapper.writerWithType(new TypeReference<List<RoleDTO>>() {}).writeValueAsString(roleList);
        } catch (DataException ex){
            throw new WebApplicationException(ex, Response.Status.SERVICE_UNAVAILABLE);

        } catch (JsonProcessingException ex) {
            throw new WebApplicationException(ex, Response.Status.INTERNAL_SERVER_ERROR);
        }catch (Exception ex){
            throw new WebApplicationException(ex, Response.Status.INTERNAL_SERVER_ERROR);
        } finally {
            destroyAfterRequest();
        }
    }
    
    /**
     * Method gets role according to identifier
     * 
     * @param id role id
     * 
     * @return JSON string containing the role
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public String getRole(@PathParam("id") Long id){
        try{
            initBeforeRequest();
            RoleDTO role = roleService.retrieveRoleById(id);
            resetRoleHeroes(role);
            return mapper.writeValueAsString(role);
        } catch (DataException ex){
            throw new WebApplicationException(ex, Response.Status.SERVICE_UNAVAILABLE);
        } catch (Exception ex) {
            throw new WebApplicationException(ex, Response.Status.INTERNAL_SERVER_ERROR);
        } finally {
            destroyAfterRequest();
        }
    }
    
    /**
     * Method creates role with REST using JSON
     * 
     * @param json JSON string containing data about role
     * 
     * @return JSON string of created role
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/post")
    public String postRole(String json){
        try{
            initBeforeRequest();
            RoleDTO role = mapper.readValue(json, new TypeReference<RoleDTO>(){});
            roleService.createRole(role);
            return mapper.writeValueAsString(role);
        } catch (DataException ex){
            throw new WebApplicationException(ex, Response.Status.SERVICE_UNAVAILABLE);
        } catch (JsonMappingException ex){
            throw new WebApplicationException(ex, Response.Status.BAD_REQUEST);
        } catch (Exception ex){
            throw new WebApplicationException(ex, Response.Status.INTERNAL_SERVER_ERROR);
        } finally {
            destroyAfterRequest();
        }
    }
    
    /**
     * Method updates role with REST using JSON
     * 
     * @param json JSON string of role
     * 
     * @return JSON string of updated role
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/put")
    public String putRole(String json){
        try{
            initBeforeRequest();
            RoleDTO role = mapper.readValue(json, new TypeReference<RoleDTO>(){});
            RoleDTO dataRole = roleService.retrieveRoleById(role.getId());
            role.setHeroes(dataRole.getHeroes());
            roleService.updateRole(role);
            resetRoleHeroes(role);
            return mapper.writeValueAsString(role);
        } catch (DataException ex){
            throw new WebApplicationException(ex, Response.Status.SERVICE_UNAVAILABLE);
        } catch (JsonMappingException ex){
            throw new WebApplicationException(ex, Response.Status.BAD_REQUEST);
        } catch (Exception ex){
            throw new WebApplicationException(ex, Response.Status.INTERNAL_SERVER_ERROR);
        } finally {
            destroyAfterRequest();
        }
    }
    
    /**
     * Method deletes role according to identifier
     * 
     * @param id role id
     * 
     * @return JSON string of deleted role
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/delete/{id}")
    public String deleteRole(@PathParam("id") Long id){
        try{
            initBeforeRequest();
            RoleDTO role = roleService.retrieveRoleById(id);
            roleService.deleteRole(role);
            resetRoleHeroes(role);
            return mapper.writeValueAsString(role);
        } catch (DataException ex){
            throw new WebApplicationException(ex, Response.Status.SERVICE_UNAVAILABLE);
        } catch (JsonMappingException ex){
            throw new WebApplicationException(ex, Response.Status.BAD_REQUEST);
        } catch (Exception ex){
            throw new WebApplicationException(ex, Response.Status.INTERNAL_SERVER_ERROR);
        } finally {
            destroyAfterRequest();
        }
    }
}