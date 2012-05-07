package com.friendster.api.beans;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root
@Namespace(reference = "http://api.friendster.com/v1/")
public class UserFriends {
	
	@Element(name = "uid")
	@Namespace(reference = "http://api.friendster.com/v1/")
	private String uid;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

}