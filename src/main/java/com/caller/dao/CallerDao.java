package com.caller.dao;

import java.util.List;

/**
 * Parent interface for all dao interfaces dealing with model objects.
 * @author SKR
 *
 */
public interface CallerDao<T, PK> {

	/**
	 * Saves given entity into DB.
	 * @param entity
	 */
	void save(T entity);
	
	/**
	 * Removes given entity.
	 * @param entity
	 */
	void remove(T entity);
	
	/**
	 * Retrieves particular entity from database upon the id basis.
	 * @param id
	 * @return
	 */
	T find(PK id);
	
	/**
	 * Finds all of the entities of the specified type.
	 * @return
	 */
	List<T> findAll();
	
}
