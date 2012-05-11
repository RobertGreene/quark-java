package com.friendster.api.beans.shoutout;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(strict = false)
@Namespace(reference = "http://api.friendster.com/v1")
public class ShoutoutResponse {

	@Element(name = "shoutouts")
	@Namespace(reference = "http://api.friendster.com/v1")
	private Shoutouts shoutouts;

	public Shoutouts getShoutouts() {
		return shoutouts;
	}

	public void setShoutouts(Shoutouts shoutouts) {
		this.shoutouts = shoutouts;
	}
	
	
	
}
