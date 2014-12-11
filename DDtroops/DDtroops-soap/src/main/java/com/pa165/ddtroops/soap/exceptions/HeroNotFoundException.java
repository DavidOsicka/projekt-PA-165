package com.pa165.ddtroops.soap.exceptions;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

/**
 *
 * @author Martin Jelínek
 */
@SoapFault(faultCode = FaultCode.SERVER, faultStringOrReason = "Hero not found."
)
public class HeroNotFoundException extends RuntimeException {

    public HeroNotFoundException(String heroName) {
        super("Hero not found '" + heroName);
    }
}
