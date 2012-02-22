package com.friendster.api.client;

import java.util.HashMap;
import java.util.Map;

import com.friendster.api.client.enums.RequestTypesEnum;
import com.friendster.api.client.request.AppDetails;
import com.friendster.api.client.response.ResponseFormat;
import com.friendster.api.v1.UserResponseType;

/* Facade for Friendster API Client
 * Friendster Inc.
 * @author Paulo Mendoza
 * Feb 21, 2012
 * 
 * This is the facade class for the Friendster API Client, it is meant to be instantiated with the session
 * scope. Multiple requests can be invoked.
 * */
public class FriendsterAPIClient {
	private AppDetails appDetails;

	public FriendsterAPIClient(String sessionKey) {
		this.appDetails = this.createAppDetails(sessionKey);
	}

	public UserResponseType getUserInformation(Object... uids) {
		RequestContext requestContext = new RequestContext(
				RequestTypesEnum.USER, this.appDetails, uids);
		return (UserResponseType) requestContext.handleRequest();
	}

	public Object getAppFriends(ResponseFormat responseFormat) {
		RequestContext requestContext = new RequestContext(
				RequestTypesEnum.APP_FRIENDS, this.appDetails);
		return requestContext.handleRequest();
	}

	public Object getFriends(ResponseFormat responseFormat, Object... uids) {
		RequestContext requestContext = new RequestContext(
				RequestTypesEnum.FRIENDS, this.appDetails, uids);
		return requestContext.handleRequest();
	}

	public Object getShoutout(ResponseFormat responseFormat, Object... uids) {
		RequestContext requestContext = new RequestContext(
				RequestTypesEnum.SHOUTOUT, this.appDetails, uids);
		return requestContext.handleRequest();
	}

	public Object getTopScores(ResponseFormat responseFormat) {
		RequestContext requestContext = new RequestContext(
				RequestTypesEnum.TOP_SCORES, this.appDetails);
		return requestContext.handleRequest();
	}

	public Object getMessages(ResponseFormat responseFormat) {
		RequestContext requestContext = new RequestContext(
				RequestTypesEnum.MESSAGES, this.appDetails);
		return requestContext.handleRequest();
	}

	public Object getMessage(ResponseFormat responseFormat, Integer cid) {
		RequestContext requestContext = new RequestContext(
				RequestTypesEnum.MESSAGE, this.appDetails, cid);
		return requestContext.handleRequest();
	}

	public Object postScore(ResponseFormat responseFormat, int avatarId,
			int score) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("score", String.valueOf(score));
		RequestContext requestContext = new RequestContext(
				RequestTypesEnum.SCORE, this.appDetails, avatarId, paramMap);
		return requestContext.handleRequest();
	}
	
	public Object postShoutout(String shoutOut) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("content", shoutOut);
		RequestContext requestContext = new RequestContext(
				RequestTypesEnum.SHOUTOUT_P, this.appDetails, paramMap);
		return requestContext.handleRequest();
	}

	private AppDetails createAppDetails(String sessionKey) {
		this.appDetails = new AppDetails();
		this.appDetails.setApiKey("6d014cc55fec6f7fc106bdbda12e7ec0");
		this.appDetails.setApiSecret("74dbc7249074d3f54690461278c4939f");
		this.appDetails.setSessionKey(sessionKey);
		return appDetails;
	}
}
