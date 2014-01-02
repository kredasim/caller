package com.caller.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Abstract parent for all daos within application.
 * @author SKR
 */
public abstract class AbstractDao<T, PK> implements CallerDao<T, PK>{

	
	@Autowired
	protected SessionFactory sessionFactory;
	
	private final Class<T> typeParameterClass;

    public AbstractDao(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }
    
    /**
     * {@inheritDoc}
     */
	public void save(T entity) {

		sessionFactory.getCurrentSession().save(entity);
	}

    /**
     * {@inheritDoc}
     */
	public void remove(T entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

    /**
     * {@inheritDoc}
     */
	public T find(PK id) {
		return (T) sessionFactory.getCurrentSession().createCriteria(typeParameterClass)
				.add(Restrictions.eq("id", id)).list().get(0);
	}

    /**
     * {@inheritDoc}
     */
	public List<T> findAll() {
		return sessionFactory.getCurrentSession().createCriteria(typeParameterClass).list();
	}
}
