package com.friendster.api.beans;

public class Shoutout extends SessionDetails {
	private String shoutoutStatus;
	private String shoutout;

	public String getShoutoutStatus() {
		return shoutoutStatus;
	}

	public void setShoutoutStatus(String shoutoutStatus) {
		this.shoutoutStatus = shoutoutStatus;
	}

	public String getShoutout() {
		return shoutout;
	}

	public void setShoutout(String shoutout) {
		this.shoutout = shoutout;
	}
}
