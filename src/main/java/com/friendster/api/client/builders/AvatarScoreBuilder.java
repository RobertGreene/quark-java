package com.friendster.api.client.builders;

import javax.xml.bind.JAXBElement;

import org.apache.log4j.Logger;

import com.friendster.api.client.special.AvatarScore;
import com.friendster.api.client.special.AvatarScoreResponse;
import com.friendster.api.v1.GameScoreResponse;
import com.friendster.api.v1.HighScores;
import com.friendster.api.v1.Score;

@SuppressWarnings("restriction")
public class AvatarScoreBuilder {
	private static Logger logger = Logger.getLogger(AvatarScoreBuilder.class);

	@SuppressWarnings({ "rawtypes" })
	public static AvatarScoreResponse buildAvatarScore(
			GameScoreResponse gameScoreResponse) {
		AvatarScoreResponse scoreList = new AvatarScoreResponse();
		HighScores highScores = gameScoreResponse.getHighScores();
		for (Score scoreObject : highScores.getScore()) {
			AvatarScore avatarScore = new AvatarScore();
			for (Object elementContent : scoreObject.getContent()) {
				if (elementContent instanceof Score) {
					Score elementScore = (Score) elementContent;
					for (Object scoreElement : elementScore.getContent()) {
						logger.debug("Unmarshalled Score : " + scoreElement);
						avatarScore.setScore((String) scoreElement);
					}
				} else if (elementContent instanceof JAXBElement) {
					JAXBElement jaxbElement = (JAXBElement) elementContent;
					String jaxbString = (String) jaxbElement.getValue();
					if (jaxbString.startsWith("2")
							&& (jaxbString.length() == 9)) {
						logger.debug("Unmarshalled Avatar ID : " + jaxbString);
						avatarScore.setAvatarId(jaxbString);
					} else {
						logger.debug("Unmarshalled Timestamp : " + jaxbString);
						avatarScore.setPostedAt(jaxbString);
					}
				}
			}
			scoreList.addScore(avatarScore);
		}
		return scoreList;
	}
}
