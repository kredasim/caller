/**
 * 
 */
package com.caller.service;

import org.springframework.transaction.annotation.Transactional;

import com.caller.model.Call;
import com.caller.model.ConferenceCall;

/**
 * @author Simeon Kredatus
 *
 */
@Transactional
public interface CallService {

	/**
	 * Initiates conference call.
	 * @param userName
	 * @param numbers
	 */
	void makeConferenceCall(String userName, String[] numbers, String conferenceRoom);
	
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
	
	/**
	 * Obtains particular conference call object from db.
	 * @param id
	 * @return
	 */
	ConferenceCall getConferenceCall(Long id);
}
