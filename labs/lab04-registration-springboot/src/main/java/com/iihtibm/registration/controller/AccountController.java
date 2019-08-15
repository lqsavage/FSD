package com.iihtibm.registration.controller;

import com.iihtibm.registration.models.User;
import com.iihtibm.registration.service.UserService;
import com.iihtibm.registration.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("account")
public class AccountController {

	public static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private UserService userService;

	// request method to create a new account by a guest
	@CrossOrigin
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User newUser) {
		if (userService.findByEmail(newUser.getEmail()) != null) {
			logger.error("username Already exist " + newUser.getUsername());
			return new ResponseEntity(
					new CustomErrorType("user with username " + newUser.getUsername() + "already exist "),
					HttpStatus.CONFLICT);
		}
		newUser.setRole("USER");
		
		return new ResponseEntity<User>(userService.create(newUser), HttpStatus.CREATED);
	}

	// this is the login api/service
	@CrossOrigin
	@RequestMapping("/login")
	public Principal user(Principal principal) {
		logger.info("user logged "+principal);
		return principal;
	}

	
	
}
