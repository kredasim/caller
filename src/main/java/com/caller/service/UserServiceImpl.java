/**
 * 
 */
package com.caller.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caller.dao.UserDao;
import com.caller.model.User;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Sms;

/**
 * @author Simeon Kredatus
 * 
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	// Find your Account Sid and Token at twilio.com/user/account
	public static final String ACCOUNT_SID = "ACbf3fda35ec89e041e56c819b54d6f053";
	public static final String AUTH_TOKEN = "b2edcb0f58accee9bd5bec09fe91d806";

	@Autowired
	private UserDao userDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerUser(User user) {
		user.setVerified(false);
		String hashedPassword = getHashedPassword(user.getPassword());
		user.setPassword(hashedPassword);
		user.setVerificationCode(String.valueOf(System.currentTimeMillis())
				+ new Random().nextInt());
		userDao.save(user);
	}
	
	/**
	 * Returns hashed password via SHA-512.
	 * @param user
	 * @return
	 */
	private String getHashedPassword(String openPassword) {
		MessageDigest sha = null;
		try {
			sha = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] data = sha.digest(openPassword.getBytes());
		return new String(Hex.encodeHex(data));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean authenticate(String userName, String password) {
		password = getHashedPassword(password);
		if (userName == null || password == null) {
			return false;
		}
		User foundUser = userDao.findByName(userName);
		if (foundUser == null) {
			return false;
		}
		return password.equals(foundUser.getPassword());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isUserActivated(String userName) {
		User foundUser = userDao.findByName(userName);
		if (foundUser == null) {
			return false;
		}
		return foundUser.isVerified();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void sendActivationCode(String name) {
		User user = userDao.findByName(name);
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

		// Build a filter for the SmsList
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("Body", "Verification code: " + user.getVerificationCode());
		params.put("To", user.getNumber());
		params.put("From", "+17242095219");

		SmsFactory smsFactory = client.getAccount().getSmsFactory();
		Sms sms = null;
		try {
			sms = smsFactory.create(params);
		} catch (TwilioRestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sms.getSid());

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void activateUser(String name, String activationNumber) {
		User user = userDao.findByName(name);
		if (activationNumber.equals(user.getVerificationCode())) {
			user.setVerified(true);
			userDao.save(user);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User getUserByName(String name) {
		return userDao.findByName(name);
	}

}
