package com.iihtibm.registration.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author savagelee
 */
public class MyLogoutHandler implements LogoutHandler {
    private static final Logger logger = LoggerFactory.getLogger(MyLogoutHandler.class);

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        logger.info("==================>>>> LogoutHandler Begin");
        logger.info(authentication.getPrincipal().toString());
        logger.info("==================>>>> LogoutHandler End");
    }
}
