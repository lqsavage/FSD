package com.iihtibm.registration.web.controller;

import com.iihtibm.registration.domain.MyUser;
import com.iihtibm.registration.exception.UserNotFoundException;
import com.iihtibm.registration.security.browser.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class TestController {
    @Autowired
    private UserDetailService userDetailService;

    @GetMapping("hello")
    public String hello() {
        return "hello spring security";
    }

    @GetMapping("index")
    public Object index(Authentication authentication) {
        // return SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

    @GetMapping("/users")
    @ResponseBody
    public List<MyUser> retrieveAllUsers() {
        return userDetailService.findAllUsers();
    }

    @GetMapping("/users/{username}")
    @ResponseBody
    public Resource<MyUser> retrieveUser(@PathVariable String username) {
        Optional<MyUser> myuser = Optional.ofNullable(userDetailService.findByUsername(username));

        if (!myuser.isPresent()) {
            throw new UserNotFoundException("The User Name - " + username);
        }

        Resource<MyUser> resource = new Resource<MyUser>(myuser.get());

        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        resource.add(linkTo.withRel("All-Users"));

        return resource;
    }

    @PutMapping("/users/{username}")
    public ResponseEntity<Object> updateStudent(@Valid @RequestBody MyUser myUser, @PathVariable String username) {

        Optional<MyUser> studentOptional = Optional.ofNullable(userDetailService.findByUsername(username));

        if (!studentOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        myUser.setUsername(username);

        userDetailService.updateUser(myUser);

        return ResponseEntity.noContent().build();
    }
}
