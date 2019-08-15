package com.iihtibm.registration.web.controller;

import com.iihtibm.registration.domain.Msg;
import com.iihtibm.registration.security.IsAdmin;
import com.iihtibm.registration.security.IsUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author savagelee
 */
@Controller
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @IsUser
    @RequestMapping("/")
    public String index(Model model){
        Msg msg =  new Msg();
        msg.setTitle("Title");
        msg.setContent("Content");
        msg.setEtraInfo("Welcome to the HOME page.");
        model.addAttribute("msg", msg);
        logger.info("Will access the HOME page...");
        return "home";
    }

    @IsAdmin
    @RequestMapping("/admin")
    @ResponseBody
    public String hello(){
        logger.info("...have ROLE_ADMIN access-Hello admin.");
        return "Hello Admin.";
    }
}
