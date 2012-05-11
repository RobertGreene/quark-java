package com.friendster.api.form;

import java.util.ArrayList;
import java.util.List;

import com.friendster.api.beans.topscores.Score;
import com.friendster.api.client.special.AvatarScore;

public class UserRequest {
	private List<Score> scores;
	private String coins;

	private String userId;

	private String name;

	private String level;
	
	private String shoutout;
	
	public UserRequest() {
		this.scores = new ArrayList<Score>();
	}
	
	public void setScore(Score avatarScore) {
		scores.add(avatarScore);
	}
	
	public List<Score> getScores() {
		return scores;
	}

	public String getCoins() {
		return coins;
	}

	public String getUserId() {
		return userId;
	}

	public void setCoins(String coins) {
		this.coins = coins;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	public String getLevel() {
		return this.level;
	}

	public String getShoutoutMsg() {
		return shoutout;
	}

	public void setShoutoutMsg(String shoutoutMsg) {
		this.shoutout = shoutoutMsg;
	}
	

}