/**
 * 
 */
package com.caller.dao;

import org.springframework.stereotype.Repository;

import com.caller.model.Call;

/**
 * @author Simeon Kredatus
 *
 */
@Repository
public class CallDaoImpl extends AbstractDao<Call, Long> implements CallDao {

	/**
	 * @param typeParameterClass
	 */
	public CallDaoImpl() {
		super(Call.class);
	}

}
