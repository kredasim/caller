/**
 * 
 */
package com.caller.rest.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Simeon Kredatus
 *
 */
@XmlRootElement(name = "Response")
public class Response {
	private String Say;
	private String Dial;
	
	/**
	 * @return the say
	 */
	public String getSay() {
		return Say;
	}
	/**
	 * @param say the say to set
	 */
	@XmlElement(name = "Say")
	public void setSay(String say) {
		Say = say;
	}
	public String getDial() {
		return Dial;
	}
	@XmlElement(name = "Dial")
	public void setDial(String dial) {
		Dial = dial;
	}
}
