
package com.pa165.ddtroops.userservice;

/**
 *
 * @author Chorke
 */
public class UserAlreadyExistsException extends CannotCreateUserException{

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
