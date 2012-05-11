package com.friendster.api.beans.topscores;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;


@Root(strict = false)
@Namespace(reference = "http://api.friendster.com/v1/")
public class GameScoreResponse {

	@Element(name = "high_scores")
	@Namespace(reference = "http://api.friendster.com/v1/")
	private HighScores highScores;

	public HighScores getHighScores() {
		return highScores;
	}

	public void setHighScores(HighScores highScores) {
		this.highScores = highScores;
	}
	
	
	
	
	
	
// Old	
//	@Element(name = "status")
//	@Namespace(reference = "http://api.friendster.com/v1/")
//	private String status;
//
//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}

}