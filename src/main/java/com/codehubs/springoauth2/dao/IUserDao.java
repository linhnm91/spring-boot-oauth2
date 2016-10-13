package com.codehubs.springoauth2.dao;

import org.hibernate.HibernateException;

import com.codehubs.springoauth2.model.User;

public interface IUserDao extends IOperations<User>{

	public User findByAccountId(String accountId) throws HibernateException, Exception;
	
	public User findByUserName(String userName) throws HibernateException, Exception;
}
