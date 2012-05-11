package com.friendster.api.beans.messages;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;


public class Messages {

	@Element(name = "conversation", required = false)
	@Namespace(reference = "http://api.friendster.com/v1/")
	private Conversation conversation;


	@Element(name = "page")
	@Namespace(reference = "http://api.friendster.com/v1/")
	private String page;

	@Element(name = "nextPage")
	@Namespace(reference = "http://api.friendster.com/v1/")
	private String nextPage;

	public String getPage() {
		return page;
	}

	public String getNextPage() {
		return nextPage;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
	
}
