package com.friendster.api.beans;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(strict = false)
@Namespace(reference = "http://api.friendster.com/v1/")
public class ApplicationGuildsResponse {

	@Element(name = "app")
	@Namespace(reference = "http://api.friendster.com/v1/")
	private String app;
	@ElementArray(entry = "guilds")
	private String[] guilds;

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String[] getGuilds() {
		return guilds;
	}

	public void setGuilds(String[] guilds) {
		this.guilds = guilds;
	}

}