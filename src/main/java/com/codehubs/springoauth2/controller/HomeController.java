package com.codehubs.springoauth2.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController{

	@RequestMapping(value="/ping", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE})
	public String ping(){
		return "pong";
	}
}
