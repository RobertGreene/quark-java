package com.friendster.api.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.friendster.api.client.enums.RequestTypesEnum;
import com.friendster.api.client.request.AppDetails;
import com.friendster.api.client.special.AvatarScoreResponse;
import com.friendster.api.client.special.MessageRequest;
import com.friendster.api.client.special.NotificationRequest;
import com.friendster.api.client.throwable.FriendsterAPIException;
import com.friendster.api.v1.ShoutoutResponse;
import com.friendster.api.v1.UserResponse;
import com.friendster.api.v1.app.ApplicationFriendsResponse;
import com.friendster.api.v1.friends.FriendsResponse;
import com.friendster.api.v1.message.MessageResponse;
import com.friendster.api.v1.notification.NotificationsResponse;
import com.friendster.api.v1.score.GameScoreResponse;

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
	private Properties configProperties;

	public FriendsterAPIClient(String sessionKey, String configFile) {
		this.configProperties = new Properties();
		try {
			this.configProperties.loadFromXML(new FileInputStream(new File(configFile)));
		} catch (InvalidPropertiesFormatException e) {
			throw new FriendsterAPIException(e);
		} catch (FileNotFoundException e) {
			throw new FriendsterAPIException(e);
		} catch (IOException e) {
			throw new FriendsterAPIException(e);
		}
		
		this.appDetails = this.createAppDetails(sessionKey);
	}

	public UserResponse getUserInformation(Object... uids) {
		RequestContext requestContext = new RequestContext(
				RequestTypesEnum.USER, this.appDetails, uids);
		return (UserResponse) requestContext.handleRequest();
	}

	public ApplicationFriendsResponse getAppFriends() {
		RequestContext requestContext = new RequestContext(
				RequestTypesEnum.APP_FRIENDS, this.appDetails);
		return (ApplicationFriendsResponse) requestContext.handleRequest();
	}

	public FriendsResponse getFriends(Integer uid) {
		RequestContext requestContext = new RequestContext(
				RequestTypesEnum.FRIENDS, this.appDetails, uid);
		return (FriendsResponse) requestContext.handleRequest();
	}

	public Object getShoutout(Object... uids) {
		RequestContext requestContext = new RequestContext(
				RequestTypesEnum.SHOUTOUT, this.appDetails, uids);
		return requestContext.handleRequest();
	}

	public AvatarScoreResponse getTopScores() {
		RequestContext requestContext = new RequestContext(
				RequestTypesEnum.TOP_SCORES, this.appDetails);
		return (AvatarScoreResponse) requestContext.handleRequest();
	}

	public com.friendster.api.v1.messages_get.MessageResponse getMessages() {
		RequestContext requestContext = new RequestContext(
				RequestTypesEnum.MESSAGES, this.appDetails);
		return (com.friendster.api.v1.messages_get.MessageResponse) requestContext.handleRequest();
	}
	
	public MessageResponse postMessage(Integer uid, MessageRequest message) {
		RequestContext requestContext = new RequestContext(RequestTypesEnum.MESSAGE_P, this.appDetails, uid, message.getRequestMap());
		return (MessageResponse) requestContext.handleRequest();
	}

	public MessageResponse getMessage(Integer cid) {
		RequestContext requestContext = new RequestContext(
				RequestTypesEnum.MESSAGE, this.appDetails, cid);
		return (MessageResponse) requestContext.handleRequest();
	}

	public GameScoreResponse postScore(int avatarId,
			int score) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("score", String.valueOf(score));
		RequestContext requestContext = new RequestContext(
				RequestTypesEnum.SCORE, this.appDetails, avatarId, paramMap);
		return (GameScoreResponse) requestContext.handleRequest();
	}
	
	public ShoutoutResponse postShoutout(String shoutOut) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("content", shoutOut);
		RequestContext requestContext = new RequestContext(
				RequestTypesEnum.SHOUTOUT_P, this.appDetails, paramMap);
		return (ShoutoutResponse) requestContext.handleRequest();
	}
	
	public NotificationsResponse postNotification(NotificationRequest request, Object... uids) {
		List<Integer> l = new ArrayList<Integer>();
		for (Object o : uids) {
			if (o instanceof Integer) {
				l.add((Integer) o);
			}
		}
		
		RequestContext requestContext = new RequestContext(RequestTypesEnum.NOTIFICATION_P, this.appDetails, request.getNotificationParams(), l);
		return (NotificationsResponse) requestContext.handleRequest();
	}
	
	public String getConfigProperty(String propertyKey) {
		return this.configProperties.getProperty(propertyKey);
	}

	private AppDetails createAppDetails(String sessionKey) {
		this.appDetails = new AppDetails();
		
		this.appDetails.setApiKey(this.getConfigProperty("api_key"));
		this.appDetails.setApiSecret(this.getConfigProperty("api_secret"));
//		this.appDetails.setApiKey("6d014cc55fec6f7fc106bdbda12e7ec0");
//		this.appDetails.setApiSecret("74dbc7249074d3f54690461278c4939f");
		this.appDetails.setSessionKey(sessionKey);
		return appDetails;
	}
}
