package com.codehubs.springoauth2.dao;

import java.io.Serializable;

import com.google.common.base.Preconditions;

public abstract class AbstractDao<T extends Serializable> implements IOperations<T> {

	protected Class<T> clazz;
	
	protected void setClazz(final Class<T> clazzToSet) {
        clazz = Preconditions.checkNotNull(clazzToSet);
    }
	
	protected Class<T> getClazz(){
		return this.clazz;
	}
}
