package com.friendster.api.beans;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(strict = false)
@Namespace(reference = "http://api.friendster.com/v1")
public class NewmessagesResponse {

	@Element(name = "new")
	private String newMsg;
	
	@Element(name = "last_update")
	private String lastUpdate;

	public String getNewMsg() {
		return newMsg;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public void setNewMsg(String newMsg) {
		this.newMsg = newMsg;
	}

	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	
	
	
	
	
}
