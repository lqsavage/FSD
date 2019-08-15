package com.iihtibm.registration.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author savagelee
 */
@Component
public class MyAuthenticationSucessHandler implements AuthenticationSuccessHandler {
/*
    private RequestCache requestCache = new HttpSessionRequestCache();

     @Autowired
    private ObjectMapper mapper;*/

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        /*response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(mapper.writeValueAsString(authentication));
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        System.out.println(savedRequest.getRedirectUrl());
        redirectStrategy.sendRedirect(request, response, savedRequest.getRedirectUrl());*/
        redirectStrategy.sendRedirect(request, response, "/");
    }
}
