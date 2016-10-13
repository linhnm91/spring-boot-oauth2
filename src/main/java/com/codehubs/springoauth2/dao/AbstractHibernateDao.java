package com.codehubs.springoauth2.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.base.Preconditions;
public abstract class AbstractHibernateDao<T extends Serializable> extends AbstractDao<T>implements IOperations<T> {
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

	// API

	@Override
	public T findOne(final long id) {
		return (T) getCurrentSession().get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return getCurrentSession().createQuery("from " + clazz.getName()).list();
	}

	@Override
	public void create(final T entity) {
		Preconditions.checkNotNull(entity);
		getCurrentSession().saveOrUpdate(entity);
	}

	@Override
	public T update(final T entity) {
		Preconditions.checkNotNull(entity);
		return (T) getCurrentSession().merge(entity);
	}

	@Override
	public void delete(final T entity) {
		Preconditions.checkNotNull(entity);
		getCurrentSession().delete(entity);
	}

	@Override
	public void deleteById(final long entityId) {
		final T entity = findOne(entityId);
		Preconditions.checkState(entity != null);
		delete(entity);
	}

	protected Session getCurrentSession() {
		try{
			return sessionFactory.getCurrentSession();
		}catch(Exception e){
			return sessionFactory.openSession();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public T getSingleResult(Query query) throws HibernateException, Exception{
		query.setMaxResults(1);
		List list = query.list();
		if(list != null && !list.isEmpty()){
			return (T)list.get(0);
		}
		return null;
	}
}
