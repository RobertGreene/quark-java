package com.friendster.api.beans;

import org.simpleframework.xml.Element;

public class MemberSince {

	@Element(name = "year", required = false)
	private String year;
	
	@Element(name = "month", required = false)
	private String month;

	public String getYear() {
		return year;
	}

	public String getMonth() {
		return month;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	
}
