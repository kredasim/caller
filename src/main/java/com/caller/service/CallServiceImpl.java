/**
 * 
 */
package com.caller.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caller.dao.CallDao;
import com.caller.dao.ConferenceCallDao;
import com.caller.model.Call;
import com.caller.model.ConferenceCall;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.instance.Account;

/**
 * @author Simeon Kredatus
 *
 */
@Service
@Transactional
public class CallServiceImpl implements CallService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CallDao callDao;
	
	@Autowired
	private ConferenceCallDao conferenceCallDao;
	
	
	private static final int SECURITY_TOKEN_TIMESTAMP_RANGE = 2000000;
	
	private static final String DEFAULT_FROM_NUMBER = "17242095219";
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void makeCall(String userName, String number, String text) {
		Call call = new Call();
		call.setFromNumber(DEFAULT_FROM_NUMBER);
		call.setToNumber(number);
		call.setMessageText(text);
		call.setSecurityToken(prepareSecurityToken());
		callDao.save(call);
		
		makeSingleCall(call.getToNumber(), call, "http://caller-simeonkredatus.rhcloud.com/call/");
	}
	
	/**
	 * Prepare security token for this call - generates random number and converts it
	 * into string.
	 * @return
	 */
	private String prepareSecurityToken() {
		int time = ((int) System.currentTimeMillis() % SECURITY_TOKEN_TIMESTAMP_RANGE);
		return String.valueOf(time + (new Random().nextLong() - SECURITY_TOKEN_TIMESTAMP_RANGE));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Call getCall(Long id) {
		return callDao.find(id);
	}

	@Override
	public void makeConferenceCall(String userName, String[] numbers, String conferenceRoom) {
		for (String number : numbers) { 
			ConferenceCall conferenceCall = new ConferenceCall();
			conferenceCall.setConferenceRoom(conferenceRoom);
			conferenceCall.setFromNumber(DEFAULT_FROM_NUMBER);
			conferenceCall.setSecurityToken(prepareSecurityToken());
			conferenceCall.setToNumber(number);
			conferenceCallDao.save(conferenceCall);
			
			makeSingleCall(number, conferenceCall, "http://caller-simeonkredatus.rhcloud.com/conference/");
		}
	}

	private void makeSingleCall(String number, Call call, String postBackUrl) {
		TwilioRestClient client = new TwilioRestClient(UserServiceImpl.ACCOUNT_SID, UserServiceImpl.AUTH_TOKEN);
		Account acct = client.getAccount();
		CallFactory callFactory = acct.getCallFactory();
		 
		//build map of post parameters 
		Map<String,String> params = new HashMap<String,String>();
		params.put("From", call.getFromNumber());
		params.put("To", number);
		StringBuilder url = 
				new StringBuilder(postBackUrl).append(call.getId())
				.append("/").append(call.getSecurityToken());
		params.put("Url", url.toString());
 
		try {
		    callFactory.create(params);
		} catch (TwilioRestException e) {
		    e.printStackTrace();
		}
	}

	@Override
	public ConferenceCall getConferenceCall(Long id) {
		return conferenceCallDao.find(id);
	}

}
