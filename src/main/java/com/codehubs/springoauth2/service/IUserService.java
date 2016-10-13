package com.codehubs.springoauth2.service;

import java.util.List;

import com.codehubs.springoauth2.model.User;

public interface IUserService {
	public User findUserByAccountId(String accountId);
	public User findUserByUserName(String userName);
	public List<User> findUsers();
	public boolean createUser(User user);
	public User updateUser(User user);
	public boolean deleteUser(String accountId);
}
