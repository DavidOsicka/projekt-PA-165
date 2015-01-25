/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa165.ddtroops.userservice;

/**
 *
 * @author Chorke
 */
public class InvalidUsernameException extends CannotCreateUserException{

    public InvalidUsernameException(String message) {
        super(message);
    }

    public InvalidUsernameException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
