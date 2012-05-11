package com.friendster.api.beans.message;

import java.util.List;

import org.simpleframework.xml.ElementList;

public class Messages {

	@ElementList(entry = "message")
	private List<Message> message;

	public List<Message> getMessage() {
		return message;
	}

	public void setMessage(List<Message> message) {
		this.message = message;
	}
	
}
