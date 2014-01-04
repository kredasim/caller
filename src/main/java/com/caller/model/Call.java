/**
 * 
 */
package com.caller.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Simeon Kredatus
 *
 */
@Entity
@Table(name="CallTable")
public class Call extends AbstractEntity<Long> {
	
	private String fromNumber;
	private String toNumber;
	private String securityToken;
	
//	@OneToOne(fetch = FetchType.EAGER)
//	@PrimaryKeyJoinColumn
//	@Transient
//	private User originator;
	private String messageText;

	/**
	 * @return the fromNumber
	 */
	public String getFromNumber() {
		return fromNumber;
	}

	/**
	 * @param fromNumber the fromNumber to set
	 */
	public void setFromNumber(String fromNumber) {
		this.fromNumber = fromNumber;
	}

	/**
	 * @return the toNumber
	 */
	public String getToNumber() {
		return toNumber;
	}

	/**
	 * @param toNumber the toNumber to set
	 */
	public void setToNumber(String toNumber) {
		this.toNumber = toNumber;
	}

	/**
	 * @return the messageText
	 */
	public String getMessageText() {
		return messageText;
	}

	/**
	 * @param messageText the messageText to set
	 */
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public String getSecurityToken() {
		return securityToken;
	}

	public void setSecurityToken(String securityToken) {
		this.securityToken = securityToken;
	}
	
	
	

}
