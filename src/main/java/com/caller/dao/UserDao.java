/**
 * 
 */
package com.caller.dao;

import com.caller.model.User;

/**
 * Dao for {@link User} objects.
 * @author Simeon Kredatus
 * 
 */
public interface UserDao extends CallerDao<User, Long> {

	User findByName(String name);
}
