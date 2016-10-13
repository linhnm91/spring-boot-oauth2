package com.codehubs.springoauth2.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.codehubs.springoauth2.dao.IUserDao;

public abstract class AbstractService {
	@Autowired
	protected IUserDao userDao;
}
