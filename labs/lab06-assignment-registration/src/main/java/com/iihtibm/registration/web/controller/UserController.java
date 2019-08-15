package com.iihtibm.registration.web.controller;

import com.iihtibm.registration.domain.Msg;
import com.iihtibm.registration.domain.MyUser;
import com.iihtibm.registration.domain.dto.MyUserDTO;
import com.iihtibm.registration.security.IsAdmin;
import com.iihtibm.registration.security.browser.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author savagelee
 */
@Controller
public class UserController {
    @Autowired
    private UserDetailService userDetailService;

    @GetMapping("/login")
    public String login(Model model) {
        return "login_2";
    }

    @IsAdmin
    @GetMapping("/registration")
    public String register(Model model) {
        return "register_2";
    }

    @IsAdmin
    @PostMapping(value = "/registration")
    public String createBin(MyUserDTO myUserDTO, Model model) throws Exception {
        System.out.println(myUserDTO.toString());
        MyUser existUser = userDetailService.findByUsername(myUserDTO.getReg_username());
        if (existUser != null) {
            model.addAttribute("error", "Existing Account. ");
        } else {
            MyUser myuser = new MyUser();
            myuser.setUsername(myUserDTO.getReg_username());
            myuser.setPassword(myUserDTO.getReg_password());
            myuser.setEmail(myUserDTO.getReg_email());
            myuser.setName(myUserDTO.getReg_fullname());
            myuser.setRole(myUserDTO.getReg_role());
            MyUser newUser = userDetailService.saveUser(myuser);
            model.addAttribute("error", "Successfully Register. ");
        }
        return "register_2";
    }
}
