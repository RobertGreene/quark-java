package com.friendster.api.beans;

import org.simpleframework.xml.Element;

public class Location {

	@Element(name = "name", required = false)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
//	@Element(name = "country")
//	private String country;
//	
//	@Element(name = "state")
//	private String state;
//	
//	@Element(name = "city")
//	private String city;
//	
//	public String getCountry() {
//		return country;
//	}
//	public String getState() {
//		return state;
//	}
//	public String getCity() {
//		return city;
//	}
//	public void setCountry(String country) {
//		this.country = country;
//	}
//	public void setState(String state) {
//		this.state = state;
//	}
//	public void setCity(String city) {
//		this.city = city;
//	}
	
	
}
