package com.caller.rest.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Response")
public class ConferenceResponse {

	private Dial dial;
	
	public ConferenceResponse() {
		
	}
	
	public ConferenceResponse(String coneferenceRoom) {
		Conference conference = new Conference();
		conference.setValue(coneferenceRoom);
		dial = new Dial();
		dial.setConference(conference);
	}

	public Dial getDial() {
		return dial;
	}

	public void setDial(Dial dial) {
		this.dial = dial;
	}
}
