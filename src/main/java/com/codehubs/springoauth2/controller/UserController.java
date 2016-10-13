package com.codehubs.springoauth2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codehubs.springoauth2.model.User;

@RestController
public class UserController extends AbstractController{
	
	@RequestMapping(value="/user", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<?> getUserList(@AuthenticationPrincipal User user){
		List<User> users = null;
		try{
			users = userService.findUsers();
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@RequestMapping(value="/user/{account_id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getUser(@PathVariable("account_id") String accountId){
		User user = null;
		try{
			user = userService.findUserByAccountId(accountId);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
}
