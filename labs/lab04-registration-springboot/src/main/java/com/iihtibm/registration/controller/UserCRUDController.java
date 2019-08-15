package com.iihtibm.registration.controller;

import com.iihtibm.registration.constants.Constant;
import com.iihtibm.registration.models.User;
import com.iihtibm.registration.service.AuthenticateService;
import com.iihtibm.registration.service.UserService;
import io.jsonwebtoken.Jwts;
import javassist.tools.rmi.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static java.lang.Long.parseLong;

@Controller
public class UserCRUDController {

    private Set<User> userList = new HashSet<User>();

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    AuthenticateService authenticateService;

    public UserCRUDController() {
    }

    @RequestMapping(value = "/data/saveUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, User> save(@RequestBody User user, HttpServletRequest request) {
        String token = request.getHeader("authorization");
        String userid = Jwts.parser().setSigningKey(Constant.KEY).parseClaimsJws(token).getBody().get("jti").toString();
        Map<String, User> saved = new HashMap<>();
        user.setId(parseLong(userid));
        int savedUser = userService.create(user);
        if (savedUser > 0) {
            saved.put("data", user);
        }
        return saved;
    }

    @RequestMapping(value = "/data/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> delete(@RequestBody User user) throws ObjectNotFoundException {

        Map<String, String> returnStatus = new HashMap<>();
        returnStatus.put("data", "deleted");
        userService.delete(user.getId());
        return returnStatus;
    }

    @RequestMapping(value = "/data/getUser", method = RequestMethod.GET)
    @ResponseBody
    public User getUsers(HttpServletRequest request) {
        String token = request.getHeader("authorization");
        User user = userService.findByUserId(Jwts.parser().setSigningKey(Constant.KEY).parseClaimsJws(token).getBody().get("jti").toString());
        return user;
    }

}
