package com.friendster.api.beans.shoutout;

import org.simpleframework.xml.Element;


public class Shoutouts {
	
	 @Element(name = "shoutout")
	 private Shoutout shoutout;

	public Shoutout getShoutout() {
		return shoutout;
	}

	public void setShoutout(Shoutout shoutout) {
		this.shoutout = shoutout;
	}
	 
	 
}
