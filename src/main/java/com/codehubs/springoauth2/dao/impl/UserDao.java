package com.codehubs.springoauth2.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.codehubs.springoauth2.dao.AbstractHibernateDao;
import com.codehubs.springoauth2.dao.IUserDao;
import com.codehubs.springoauth2.model.User;
@Repository
@Transactional
public class UserDao extends AbstractHibernateDao<User>implements IUserDao {

	public UserDao() {
		super();
		setClazz(User.class);
	}

	@Override
	public User findByAccountId(String accountId) throws HibernateException, Exception {
		String sql = "FROM User WHERE accountId = :accountId";
		Query query = getCurrentSession().createQuery(sql);
		query.setParameter("accountId", accountId);
		return getSingleResult(query);
	}

	@Override
	public User findByUserName(String userName) throws HibernateException, Exception {
		String sql = "FROM User WHERE username = :username";
		Query query = getCurrentSession().createQuery(sql);
		query.setParameter("username", userName);
		return getSingleResult(query);
	}

}
