/**
 * 
 */
package com.caller.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caller.dao.CallDao;
import com.caller.model.Call;
import com.caller.model.User;
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
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void makeCall(String userName, String number, String text) {
		User user = userService.getUserByName(userName);
		Call call = new Call();
		call.setFromNumber("17242095219");
		call.setToNumber(number);
//		call.setOriginator(user);
		call.setMessageText(text);
		
		callDao.save(call);
		
		/* Instantiate a new Twilio Rest Client */
        TwilioRestClient client = new TwilioRestClient(UserServiceImpl.ACCOUNT_SID, UserServiceImpl.AUTH_TOKEN);
 
        // Get the account and call factory class
        Account acct = client.getAccount();
        CallFactory callFactory = acct.getCallFactory();
         
        //build map of post parameters 
        Map<String,String> params = new HashMap<String,String>();
        params.put("From", call.getFromNumber());
        params.put("To", call.getToNumber());
        StringBuilder url = 
        		new StringBuilder("http://caller-simeonkredatus.rhcloud.com/call/").append(call.getId());
        params.put("Url", url.toString());
 
        try {
            // Make a phone call  ( This makes a POST request to the Calls resource)
            callFactory.create(params);
        } catch (TwilioRestException e) {
            e.printStackTrace();
        }
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Call getCall(Long id) {
		return callDao.find(id);
	}

}
