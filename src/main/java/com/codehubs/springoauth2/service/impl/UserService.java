package com.codehubs.springoauth2.service.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codehubs.springoauth2.model.User;
import com.codehubs.springoauth2.service.AbstractService;
import com.codehubs.springoauth2.service.IUserService;

@Service
@Transactional(rollbackFor= Exception.class)
public class UserService extends AbstractService implements IUserService{

	@Override
	public User findUserByAccountId(String accountId) {
		User user = null;
		try {
			user = userDao.findByAccountId(accountId);
		} catch(HibernateException e){
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User findUserByUserName(String userName) {
		User user = null;
		try{
			user = userDao.findByUserName(userName);
		}catch(HibernateException e){
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> findUsers() {
		List<User> users = null;
		try{
			users = userDao.findAll();
		}catch(HibernateException he){
			he.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public boolean createUser(User user) {
		try{
			userDao.create(user);
			return true;
		}catch(HibernateException he){
			he.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User updateUser(User user) {
	
		try{
			return userDao.update(user);
			
		}catch(HibernateException he){
			he.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteUser(String accountId) {
		try{
			User user = null;
			user = userDao.findByAccountId(accountId);
			if(user == null){
				throw new Exception("User is not existed.");
			}
			userDao.delete(user);
			return true;
		}catch(HibernateException he){
			he.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
