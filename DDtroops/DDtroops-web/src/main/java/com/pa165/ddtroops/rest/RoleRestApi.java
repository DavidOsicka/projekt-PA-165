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

/**
 * REST Web Service
 *
 * @author Jakub
 */
@Path("/role")
public class RoleRestApi {

    private static final XmlWebApplicationContext APP_CONF =
            new XmlWebApplicationContext();
    
    @Context
    private ServletContext context;
    private RoleService roleService;
    private ObjectMapper mapper = new ObjectMapper();

    public RoleRestApi() {
        APP_CONF.setNamespace("applicationContext-web");
    }
    
    private void initBeforeRequest() {
        APP_CONF.setServletContext(context);
        APP_CONF.refresh();
        roleService = APP_CONF.getBean(RoleService.class);
        
    }
    
    private void destroyAfterRequest(){
        
    }
    
    private void resetRoleHeroes(RoleDTO r) {
        r.setHeroes(new HashSet<HeroDTO>());
    }
    
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