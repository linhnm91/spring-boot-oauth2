package com.codehubs.springoauth2.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.codehubs.springoauth2.service.IUserService;

public abstract class AbstractController {

	@Autowired
	protected IUserService userService;
}
