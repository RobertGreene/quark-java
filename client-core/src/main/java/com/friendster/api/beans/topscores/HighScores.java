package com.friendster.api.beans.topscores;

import java.util.List;

import org.simpleframework.xml.ElementList;


public class HighScores {

	@ElementList(inline = true, entry = "score", required = false)
	private List<Score> score;

	public List<Score> getScore() {
		return score;
	}

	public void setScore(List<Score> score) {
		this.score = score;
	}
	
	

	
	
}
