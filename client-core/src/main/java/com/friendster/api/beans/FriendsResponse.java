package com.friendster.api.beans;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(strict = false)
@Namespace(reference = "http://api.friendster.com/v1/")
public class FriendsResponse {

	@ElementList(entry = "friends")
	@Namespace(reference = "http://api.friendster.com/v1/")
	//private List<UserFriends> friends;
	private List<String> uid;

	public List<String> getUid() {
		return uid;
	}

	public void setUid(List<String> uid) {
		this.uid = uid;
	}

	

//	public List<UserFriends> getFriends() {
//		return friends;
//	}
//
//	public void setFriends(List<UserFriends> friends) {
//		this.friends = friends;
//	}

}