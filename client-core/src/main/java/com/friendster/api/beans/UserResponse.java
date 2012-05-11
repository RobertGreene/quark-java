package com.friendster.api.beans;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(strict = false)
@Namespace(reference = "http://api.friendster.com/v1/")
public class UserResponse {
	
	@ElementList(inline = true, name = "user", required = false)
	@Namespace(reference = "http://api.friendster.com/v1/")
	private List<User> user;

	
	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}
}
