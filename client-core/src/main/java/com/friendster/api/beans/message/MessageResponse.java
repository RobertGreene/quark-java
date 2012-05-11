package com.friendster.api.beans.message;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(strict = false)
@Namespace(reference = "http://api.friendster.com/v1")
public class MessageResponse {

	@Element(name = "messages", required = false)
	@Namespace(reference = "http://api.friendster.com/v1")
	private Messages messages;

	public Messages getMessages() {
		return messages;
	}

	public void setMessages(Messages messages) {
		this.messages = messages;
	}
}