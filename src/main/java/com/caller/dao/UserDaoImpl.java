/**
 * 
 */
package com.caller.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.caller.model.User;

/**
 * @author Simeon Kredatus
 * 
 */
@Repository
public class UserDaoImpl extends AbstractDao<User, Long> implements UserDao {

	/**
	 * @param typeParameterClass
	 */
	public UserDaoImpl() {
		super(User.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User findByName(String name) {
		List users = sessionFactory.getCurrentSession()
				.createCriteria(User.class).add(Restrictions.eq("name", name))
				.list();
		if (users.size() > 0) {
			return (User) users.get(0);
		}
		return null;
	}

}
