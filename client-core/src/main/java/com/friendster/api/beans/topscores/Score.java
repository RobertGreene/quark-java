package com.friendster.api.beans.topscores;

import org.simpleframework.xml.Element;

public class Score {
	
	@Element(name = "avatar_id", required = false)
	private String avatar_id;
	
	@Element(name = "score", required = false)
	private String topScore;
	
	@Element(name = "posted_at", required = false)
	private String posted_at;
	
	public String getAvatar_id() {
		return avatar_id;
	}

	
	public String getPosted_at() {
		return posted_at;
	}
	
	public void setAvatar_id(String avatar_id) {
		this.avatar_id = avatar_id;
	}

	
	public void setPosted_at(String posted_at) {
		this.posted_at = posted_at;
	}


	public String getTopScore() {
		return topScore;
	}


	public void setTopScore(String topScore) {
		this.topScore = topScore;
	}
	
	
}
