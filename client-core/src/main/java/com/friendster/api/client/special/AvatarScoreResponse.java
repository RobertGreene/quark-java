package com.friendster.api.client.special;

import java.util.List;
import java.util.ArrayList;

public class AvatarScoreResponse {

	private List<AvatarScore> avatarScores;

	public AvatarScoreResponse() {
		this.avatarScores = new ArrayList<AvatarScore>();
	}

	public List<AvatarScore> getScores() {
		return this.avatarScores;
	}

	public void addScore(AvatarScore avatarScore) {
		avatarScores.add(avatarScore);
	}

}
