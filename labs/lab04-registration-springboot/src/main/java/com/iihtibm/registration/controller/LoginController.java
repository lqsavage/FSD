package com.iihtibm.registration.controller;

import com.iihtibm.registration.models.User;
import com.iihtibm.registration.service.AuthenticateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller

public class LoginController {

    @Autowired
    AuthenticateService authenticateService;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login", method = RequestMethod.POST)

    @ResponseBody
    public Map<String, String> login(@RequestBody User user) {
        Map<String, String> valid = new HashMap<String, String>();
        valid.put("valid", "false");
        boolean isValid = authenticateService.IsValidUser(user);
        if (isValid) {
            valid.put("valid", "true");
            valid.put("jwt", authenticateService.getJWT(user));
        }
        return valid;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> register(@RequestBody User user) {
        Map<String, String> valid = new HashMap<String, String>();
        valid.put("valid", "false");
        boolean success = authenticateService.registerUser(user);
        if (success) {
            valid.put("valid", "true");
        }
        return valid;
    }

}
