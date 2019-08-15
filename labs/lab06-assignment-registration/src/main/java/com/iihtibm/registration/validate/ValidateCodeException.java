package com.iihtibm.registration.validate;

import org.springframework.security.core.AuthenticationException;

/**
 * @author savagelee
 */
public class ValidateCodeException extends AuthenticationException {
    private static final long serialVersionUID = 5022575393500654458L;

    ValidateCodeException(String message) {
        super(message);
    }
}
