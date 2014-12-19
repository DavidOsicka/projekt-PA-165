/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.ddtroops.console.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pa165.ddtroops.api.dto.HeroDTO;
import com.pa165.ddtroops.api.dto.RoleDTO;
import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/**
 * Application class of web service console client
 * 
 * @version 1.0,19/12/2014
 * @author Jakub Szotkowski
 */
public class Application {
    private static ObjectMapper mapper = new ObjectMapper();
    
    public static void main(String[] args) {
        
        switch(args[0]) {
            case "listHeroes": 
                getAllHeroes(); 
                break;
            case "getHero": 
                getHero(args[1]);
                break;
            case "createHero": 
                createHero(args[1], args[2], args[3]);
                break;
            case "updateHero":
                updateHero(args[1], args[2], args[3], args[4]);
                break;
            case "deleteHero": 
                deleteHero(args[1]);
                break;
            case "listRoles": 
                getAllRoles(); 
                break;
            case "getRole": 
                getRole(args[1]);
                break;
            case "createRole": 
                createRole(args[1], args[2], args[3], args[4], args[5]);
                break;
            case "updateRole":
                updateRole(args[1], args[2], args[3], args[4], args[5], args[6]);
                break;
            case "deleteRole": 
                deleteRole(args[1]);
                break;
            case "help":
                printHelp();
                break;
            default: 
                System.out.println("Error occured when getting information or any command was wrong!\nFor help write command: help"); 
                break;
        }

    }
    
    private static void getAllHeroes() {
        try {
            
            //getAllHeroes
                URL url = new URL("http://localhost:8080/pa165/rest-jersey-server/hero");
                URLConnection connection = url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
 
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String s = "";
                List<HeroDTO> hero = new ArrayList<HeroDTO>();
                while ((s = in.readLine()) != null) {
                     hero = mapper.readValue(s, new TypeReference<List<HeroDTO>>(){});
                }
                System.out.println("All heroes");
                for(HeroDTO h : hero) {
                    System.out.println("ID: " + h.getId() + ", NAME: " + h.getName() + ", RACE: " + h.getRace() + ", XP: " + h.getXp());
                }
                System.out.println();
                in.close();
            } catch (Exception e) {
                System.out.println("\nError when returning all heroes");
                System.out.println(e);
            }
    }
    
    private static void getHero(String number) {
        try {
            //get hero
            
            URL url = new URL("http://localhost:8080/pa165/rest-jersey-server/hero/" + number);
                URLConnection connection = url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String s = "";
                HeroDTO hero = new HeroDTO();
                while ((s = in.readLine()) != null) {
                     hero = mapper.readValue(s, new TypeReference<HeroDTO>(){});
                }
                System.out.println("Returned hero");
                System.out.println("ID: " + hero.getId() + ", NAME: " + hero.getName() + ", RACE: " + hero.getRace() + ", XP: " + hero.getXp());
                System.out.println();
                in.close();
        } catch (Exception e){
            System.out.println("\nError when returning hero");
            System.out.println(e);
        }
    }
    
    private static void createHero(String name, String race, String xp) {
        try {
            //create new hero
            HeroDTO h = new HeroDTO();
            h.setName(name);
            h.setRace(race);
            h.setXp(Integer.parseInt(xp));
            JSONObject jsonObject = new JSONObject(h);
            
            URL url = new URL("http://localhost:8080/pa165/rest-jersey-server/hero/post");
                URLConnection connection = url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                out.write(jsonObject.toString());
                out.close();
                
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));

                while (in.readLine() != null) {
                }
                System.out.println("New hero " + h.getName() + " was created.");
                System.out.println();
                in.close();
        } catch (Exception e){
            System.out.println("\nError when creating new hero");
            System.out.println(e);
        }
    }
    
    private static void updateHero(String id, String name, String race, String xp) {
        try {
            //update hero
            HeroDTO h = new HeroDTO();
            h.setId(Long.parseLong(id));
            h.setName(name);
            h.setRace(race);
            h.setXp(Integer.parseInt(xp));
            JSONObject jsonObject = new JSONObject(h);
            
            URL url = new URL("http://localhost:8080/pa165/rest-jersey-server/hero/put");
                URLConnection connection = url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                out.write(jsonObject.toString());
                out.close();
                
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                while (in.readLine() != null) {
                }
                System.out.println("Updated hero with id: " +h.getId());
                System.out.println();
                in.close();
        } catch (Exception e){
            System.out.println("\nError when updating hero");
            System.out.println(e);
        }
    }
    
    private static void deleteHero(String number) {
        try {
            //delete hero
            
            URL url = new URL("http://localhost:8080/pa165/rest-jersey-server/hero/delete/" + number);
                URLConnection connection = url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                while (in.readLine() != null) {
                }
                System.out.println("Hero with id: " + number + " was deleted");
                System.out.println();
                in.close();
        } catch (Exception e){
            System.out.println("\nError when deleting hero");
            System.out.println(e);
        }
    }
    
    private static void getAllRoles() {
        try {
            
            //getAllRoles
                URL url = new URL("http://localhost:8080/pa165/rest-jersey-server/role");
                URLConnection connection = url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
 
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String s = "";
                List<RoleDTO> role = new ArrayList<RoleDTO>();
                while ((s = in.readLine()) != null) {
                     role = mapper.readValue(s, new TypeReference<List<RoleDTO>>(){});
                }
                System.out.println("All roles");
                for(RoleDTO r : role) {
                    System.out.println("ID: " + r.getId() + ", NAME: " + r.getName() + ", DESCRIPTION: " + r.getDescription() + ", ENERGY: " + r.getEnergy()+ ", ATTACK: " + r.getAttack() + ", DEFENSE: " + r.getDefense());
                }
                System.out.println();
                in.close();
            } catch (Exception e) {
                System.out.println("\nError when returning all roles");
                System.out.println(e);
            }
    }
    
    private static void getRole(String number) {
        try {
            //get role
            
            URL url = new URL("http://localhost:8080/pa165/rest-jersey-server/role/" + number);
                URLConnection connection = url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String s = "";
                RoleDTO role = new RoleDTO();
                while ((s = in.readLine()) != null) {
                     role = mapper.readValue(s, new TypeReference<RoleDTO>(){});
                }
                System.out.println("Returned role");
                System.out.println("ID: " + role.getId() + ", NAME: " + role.getName() + ", DESCRIPTION: " + role.getDescription() + ", ENERGY: " + role.getEnergy()+ ", ATTACK: " + role.getAttack() + ", DEFENSE: " + role.getDefense());
                System.out.println();
                in.close();
        } catch (Exception e){
            System.out.println("\nError when returning role");
            System.out.println(e);
        }
    }
    
    private static void createRole(String name, String description, String energy, String attack, String defense) {
        try {
            //create new role
            RoleDTO r = new RoleDTO();
            r.setName(name);
            r.setDescription(description);
            r.setEnergy(Integer.parseInt(energy));
            r.setAttack(Integer.parseInt(attack));
            r.setDefense(Integer.parseInt(defense));
            JSONObject jsonObject = new JSONObject(r);
            
            URL url = new URL("http://localhost:8080/pa165/rest-jersey-server/role/post");
                URLConnection connection = url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                out.write(jsonObject.toString());
                out.close();
                
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));

                while (in.readLine() != null) {
                }
                System.out.println("New role " + r.getName() + " was created.");
                System.out.println();
                in.close();
        } catch (Exception e){
            System.out.println("\nError when creating new role");
            System.out.println(e);
        }
    }
    
    private static void updateRole(String id, String name, String description, String energy, String attack, String defense) {
        try {
            //update role
            RoleDTO r = new RoleDTO();
            r.setId(Long.parseLong(id));
            r.setName(name);
            r.setDescription(description);
            r.setEnergy(Integer.parseInt(energy));
            r.setAttack(Integer.parseInt(attack));
            r.setDefense(Integer.parseInt(defense));
            JSONObject jsonObject = new JSONObject(r);
            
            URL url = new URL("http://localhost:8080/pa165/rest-jersey-server/role/put");
                URLConnection connection = url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                out.write(jsonObject.toString());
                out.close();
                
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                while (in.readLine() != null) {
                }
                System.out.println("Updated role with id: " +r.getId());
                System.out.println();
                in.close();
        } catch (Exception e){
            System.out.println("\nError when updating role");
            System.out.println(e);
        }
    }
    
    private static void deleteRole(String number) {
        try {
            //delete role
            
            URL url = new URL("http://localhost:8080/pa165/rest-jersey-server/role/delete/" + number);
                URLConnection connection = url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                while (in.readLine() != null) {
                }
                System.out.println("Role with id: " + number + " was deleted");
                System.out.println();
                in.close();
        } catch (Exception e){
            System.out.println("\nError when deleting role");
            System.out.println(e);
        }
    }
    
    private static void printHelp() {
        System.out.println("All commands must write as arguments.\n");
        System.out.println("Commands for hero:\n");
        System.out.println("Print list of all heroes - listHeroes");
        System.out.println("Get hero - getHero id");
        System.out.println("Create new hero - createHero name race xp");
        System.out.println("Update hero - updateHero id name race xp");
        System.out.println("Delete hero - deleteHero id\n");
        System.out.println("Commands for role:\n");
        System.out.println("Print list of all roles - listRoles");
        System.out.println("Get role - getRole id");
        System.out.println("Create new role - createRole name description energy attack defense");
        System.out.println("Update role - updateRole id name description energy attack defense");
        System.out.println("Delete role - deleteRole id\n");
    }   
}
