package com.friendster.api.beans;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(strict = false)
@Namespace(reference = "http://api.friendster.com/v1/")
public class ApplicationFriendsResponse {

	@ElementList(name = "application_friends")
	@Namespace(reference = "http://api.friendster.com/v1/")

	private List<String> uid;

	public List<String> getUid() {
		return uid;
	}

	public void setUid(List<String> uid) {
		this.uid = uid;
	}
}