
package com.pa165.ddtroops.userservice;

/**
 *
 * @author Chorke
 */
public class CannotCreateUserException extends RuntimeException{

    public CannotCreateUserException(String message) {
        super(message);
    }

    public CannotCreateUserException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
