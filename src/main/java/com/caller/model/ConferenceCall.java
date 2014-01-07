package com.caller.model;

import javax.persistence.Entity;

@Entity
public class ConferenceCall extends Call {

	private String conferenceRoom;

	public String getConferenceRoom() {
		return conferenceRoom;
	}

	public void setConferenceRoom(String conferenceRoom) {
		this.conferenceRoom = conferenceRoom;
	}
}
