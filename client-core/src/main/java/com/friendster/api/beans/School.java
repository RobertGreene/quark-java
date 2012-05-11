package com.friendster.api.beans;

import org.simpleframework.xml.Element;

public class School {
	
	@Element(name = "name", required = false)
	private String name;
	
	@Element(name = "region", required = false)
	private String region;
	
	@Element(name = "country", required = false)
	private String country;
	
	@Element(name = "city", required = false)
	private String city;
	
	@Element(name = "year_start", required = false)
	private String year_start;
	
	@Element(name = "year_end", required = false)
	private String year_end;
	
	@Element(name = "major", required = false)
	private String major;
	
	@Element(name = "degree", required = false)
	private String degree;
	
	@Element(name = "course", required = false)
	private String course;
	
	@Element(name = "class_of", required = false)
	private String class_of;

	public String getName() {
		return name;
	}

	public String getRegion() {
		return region;
	}

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public String getYear_start() {
		return year_start;
	}

	public String getYear_end() {
		return year_end;
	}

	public String getMajor() {
		return major;
	}

	public String getDegree() {
		return degree;
	}

	public String getCourse() {
		return course;
	}

	public String getClass_of() {
		return class_of;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setYear_start(String year_start) {
		this.year_start = year_start;
	}

	public void setYear_end(String year_end) {
		this.year_end = year_end;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public void setClass_of(String class_of) {
		this.class_of = class_of;
	}
}
