package com.friendster.api.beans.messages;

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;


public class Messages {

	@ElementList(inline = true, entry = "conversation", required = false)
	@Namespace(reference = "http://api.friendster.com/v1/")
	private List<Conversation> conversation;
	
	@Element(name = "page", required = false)
	@Namespace(reference = "http://api.friendster.com/v1/")
	private String page;

	@Element(name = "next_page", required = false)
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

	public List<Conversation> getConversation() {
		return conversation;
	}

	public void setConversation(List<Conversation> conversation) {
		this.conversation = conversation;
	}
	
}
