package com.friendster.api.beans;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(strict = false)
@Namespace(reference = "http://api.friendster.com/v1/")
public class NotificationsResponse {

	@ElementList(inline = true, entry = "uid", name = "friends")
	@Namespace(reference = "http://api.friendster.com/v1/")
	private List<String> uids;

	public List<String> getUids() {
		return uids;
	}

	public void setUids(List<String> uids) {
		this.uids = uids;
	}

}