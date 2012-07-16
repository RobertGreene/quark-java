package com.friendster.api.beans;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(strict = false)
@Namespace(reference = "http://api.friendster.com/v1/")
public class PointsResponse {
	@Element(name = "uid", required = false)
	private Integer uid;
	@Element(name = "points", required = false)
	private Integer points;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

}