package com.caller.dao;

import org.springframework.stereotype.Repository;

import com.caller.model.ConferenceCall;

/**
 * The implementation of {@link ConferenceCall}.
 * @author SKR
 *
 */
@Repository
public class ConferenceCallDaoImpl extends AbstractDao<ConferenceCall, Long> implements ConferenceCallDao {

	public ConferenceCallDaoImpl() {
		super(ConferenceCall.class);
	}
}
