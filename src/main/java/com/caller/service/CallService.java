/**
 * 
 */
package com.caller.service;

import org.springframework.transaction.annotation.Transactional;

import com.caller.model.Call;

/**
 * @author Simeon Kredatus
 *
 */
@Transactional
public interface CallService {

	/**
	 * Makes call to the given number and says the given text.
	 * @param userName
	 * @param number
	 */
	void makeCall(String userName, String number, String text);
	
	/**
	 * Obtains the particular call object from db.
	 * @param id
	 * @return
	 */
	Call getCall(Long id);
	
}
