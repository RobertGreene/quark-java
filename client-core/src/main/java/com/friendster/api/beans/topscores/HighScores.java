package com.friendster.api.beans.topscores;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

public class HighScores {

	@ElementList(inline = true, entry = "score", required = false)
	private List<Score> score;
	
	@Attribute
	private boolean list;

	public List<Score> getScore() {
		return score;
	}

	public void setScore(List<Score> score) {
		this.score = score;
	}

	public boolean isList() {
		return list;
	}

	public void setList(boolean list) {
		this.list = list;
	}
		
}
