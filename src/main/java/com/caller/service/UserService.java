/**
 * 
 */
package com.caller.service;

import com.caller.model.User;

/**
 * @author Simeon Kredatus
 *
 */
public interface UserService {

	/**
	 * Registers user.
	 * @param user
	 */
	void registerUser(User user);
	
	/**
	 * Authenticates user's credentials.
	 * @param userName
	 * @param password
	 * @return
	 */
	boolean authenticate(String userName, String password);
	
	/**
	 * Tests whether user specified by its name has been activated.
	 * @param userName
	 * @return
	 */
	boolean isUserActivated(String userName);

	/**
	 * Sends sms to the user with it's activation code.
	 * @param name
	 */
	void sendActivationCode(String name);

	/**
	 * Activates given user.
	 * @param name
	 * @param activationNumber
	 */
	void activateUser(String name, String activationNumber);
	
	/**
	 * Gets the user by its name.
	 * @param name
	 * @return
	 */
	User getUserByName(String name);
}
