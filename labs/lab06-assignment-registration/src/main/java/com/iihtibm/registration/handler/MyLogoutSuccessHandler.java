package com.iihtibm.registration.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author savagelee
 */
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    private static final Logger logger = LoggerFactory.getLogger(MyLogoutSuccessHandler.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        logger.info("==================>>>> LogoutSuccessHandler Begin");
        logger.info(authentication.getPrincipal().toString());
        logger.info("==================>>>> LogoutSuccessHandler End");
    }
}
