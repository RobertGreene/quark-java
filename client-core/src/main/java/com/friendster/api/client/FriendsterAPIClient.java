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

import com.friendster.api.client.enums.RequestType;
import com.friendster.api.client.request.FriendsterPCPAppInfo;
import com.friendster.api.client.special.AvatarScoreResponse;
import com.friendster.api.client.special.MessageRequest;
import com.friendster.api.client.special.NotificationRequest;
import com.friendster.api.client.special.PaymentRequest;
import com.friendster.api.client.throwable.FriendsterAPIException;
import com.friendster.api.v1.ShoutoutResponse;
import com.friendster.api.v1.UserResponse;
import com.friendster.api.v1.WalletResponse;
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
	private FriendsterPCPAppInfo appDetails;
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
	
	public FriendsterAPIClient(String sessionKey, String apiKey, String apiSecret) {
		this.appDetails = new FriendsterPCPAppInfo();
		
		this.appDetails.setApiKey(apiKey);
		this.appDetails.setApiSecret(apiSecret);
		this.appDetails.setSessionKey(sessionKey);
	}

	public UserResponse getUserInformation(Object... uids) {
		RequestContext requestContext = new RequestContext(
				RequestType.USER, this.appDetails, uids);
		return (UserResponse) requestContext.handleRequest();
	}

	public ApplicationFriendsResponse getAppFriends() {
		RequestContext requestContext = new RequestContext(
				RequestType.APP_FRIENDS, this.appDetails);
		return (ApplicationFriendsResponse) requestContext.handleRequest();
	}

	public FriendsResponse getFriends(Integer uid) {
		RequestContext requestContext = new RequestContext(
				RequestType.FRIENDS, this.appDetails, uid);
		return (FriendsResponse) requestContext.handleRequest();
	}

	public com.friendster.api.v1.shoutout_list.ShoutoutResponse getShoutout(Object... uids) {
		RequestContext requestContext = new RequestContext(
				RequestType.SHOUTOUT, this.appDetails, uids);
		return (com.friendster.api.v1.shoutout_list.ShoutoutResponse) requestContext.handleRequest();
	}

	public AvatarScoreResponse getTopScores() {
		RequestContext requestContext = new RequestContext(
				RequestType.TOP_SCORES, this.appDetails);
		return (AvatarScoreResponse) requestContext.handleRequest();
	}

	public com.friendster.api.v1.messages_get.MessageResponse getMessages() {
		RequestContext requestContext = new RequestContext(
				RequestType.MESSAGES, this.appDetails);
		return (com.friendster.api.v1.messages_get.MessageResponse) requestContext.handleRequest();
	}
	
	public MessageResponse postMessage(Integer uid, MessageRequest message) {
		RequestContext requestContext = new RequestContext(RequestType.MESSAGE_P, this.appDetails, uid, message.getRequestMap());
		return (MessageResponse) requestContext.handleRequest();
	}

	public MessageResponse getMessage(Integer cid) {
		RequestContext requestContext = new RequestContext(
				RequestType.MESSAGE, this.appDetails, cid);
		return (MessageResponse) requestContext.handleRequest();
	}

	public GameScoreResponse postScore(int avatarId,
			int score) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("score", String.valueOf(score));
		RequestContext requestContext = new RequestContext(
				RequestType.SCORE, this.appDetails, avatarId, paramMap);
		return (GameScoreResponse) requestContext.handleRequest();
	}
	
	public ShoutoutResponse postShoutout(String shoutOut) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("content", shoutOut);
		RequestContext requestContext = new RequestContext(
				RequestType.SHOUTOUT_P, this.appDetails, paramMap);
		return (ShoutoutResponse) requestContext.handleRequest();
	}
	
	public NotificationsResponse postNotification(NotificationRequest request, Object... uids) {
		List<Integer> uidList = new ArrayList<Integer>();
		for (Object o : uids) {
			if (o instanceof Integer) {
				uidList.add((Integer) o);
			}
		}
		RequestContext requestContext = new RequestContext(RequestType.NOTIFICATION_P, this.appDetails, request.getNotificationParams(), uidList);
		return (NotificationsResponse) requestContext.handleRequest();
	}
	
	public WalletResponse getWalletBalance() {
		RequestContext requestContext = new RequestContext(RequestType.WALLET_BALANCE, this.appDetails);
		return (WalletResponse) requestContext.handleRequest();
	}
	
	public WalletResponse getPaymentRequest(PaymentRequest request) {
		RequestContext requestContext = new RequestContext(RequestType.WALLET_GET, this.appDetails, request.getPaymentParams());
		return (WalletResponse) requestContext.handleRequest();
	}
	
	public WalletResponse commitPaymentRequest(String requestToken) {
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("request_token", requestToken);
		RequestContext requestContext = new RequestContext(RequestType.WALLET_COMMIT, this.appDetails);
		return (WalletResponse) requestContext.handleRequest();
	}
	
	private String getConfigProperty(String propertyKey) {
		return this.configProperties.getProperty(propertyKey);
	}

	private FriendsterPCPAppInfo createAppDetails(String sessionKey) {
		this.appDetails = new FriendsterPCPAppInfo();
		
		this.appDetails.setApiKey(this.getConfigProperty("api_key"));
		this.appDetails.setApiSecret(this.getConfigProperty("api_secret"));
		this.appDetails.setSessionKey(sessionKey);
		return appDetails;
	}
}
