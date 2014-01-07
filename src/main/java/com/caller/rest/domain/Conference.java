package com.caller.rest.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class Conference {

	
	private String waitUrl;

	
	private String value;
	
	@XmlAttribute
	public String getWaitUrl() {
		return waitUrl;
	}

	public void setWaitUrl(String waitUrl) {
		this.waitUrl = waitUrl;
	}

	@XmlValue
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
