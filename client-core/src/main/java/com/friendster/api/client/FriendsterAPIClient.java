package com.friendster.api.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.friendster.api.beans.ApplicationFriendsResponse;
import com.friendster.api.beans.AssetResponse;
import com.friendster.api.beans.FriendsResponse;
import com.friendster.api.beans.GameScoreResponse;
import com.friendster.api.beans.MessageResponse;
import com.friendster.api.beans.NotificationsResponse;
import com.friendster.api.beans.PointsResponse;
import com.friendster.api.beans.UserResponse;
import com.friendster.api.beans.wallet.payment.WalletResponse;
import com.friendster.api.client.enums.RequestType;
import com.friendster.api.client.request.FriendsterPCPAppInfo;
import com.friendster.api.client.special.MessageRequest;
import com.friendster.api.client.special.NotificationRequest;
import com.friendster.api.client.special.PaymentRequest;
import com.friendster.api.client.throwable.FriendsterAPIException;
import com.friendster.client.example.ReadAsset;

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
			this.configProperties.loadFromXML(new FileInputStream(new File(
					configFile)));
		} catch (InvalidPropertiesFormatException e) {
			throw new FriendsterAPIException(e);
		} catch (FileNotFoundException e) {
			throw new FriendsterAPIException(e);
		} catch (IOException e) {
			throw new FriendsterAPIException(e);
		}

		this.appDetails = this.createAppDetails(sessionKey);
	}

	public FriendsterAPIClient(FriendsterPCPAppInfo appDetails) {
		this.appDetails = appDetails;
	}

	public void setSessionKey(String sessionKey) {
		this.appDetails.setSessionKey(sessionKey);
	}

	public FriendsterAPIClient(String sessionKey, String apiKey,
			String apiSecret) {
		this.appDetails = new FriendsterPCPAppInfo();

		this.appDetails.setApiKey(apiKey);
		this.appDetails.setApiSecret(apiSecret);
		this.appDetails.setSessionKey(sessionKey);
	}

	public UserResponse getUserInformation(Object... uids) {
		RequestContext requestContext = new RequestContext(RequestType.USER,
				this.appDetails, uids);
		return (UserResponse) requestContext.handleRequest();
	}

	public ApplicationFriendsResponse getAppFriends() {
		RequestContext requestContext = new RequestContext(
				RequestType.APP_FRIENDS, this.appDetails);
		return (ApplicationFriendsResponse) requestContext.handleRequest();
	}

	public FriendsResponse getFriends(Integer uid) {
		RequestContext requestContext = new RequestContext(RequestType.FRIENDS,
				this.appDetails, uid);
		return (FriendsResponse) requestContext.handleRequest();
	}

	public com.friendster.api.beans.shoutout.ShoutoutResponse getShoutout(
			Object... uids) {
		RequestContext requestContext = new RequestContext(
				RequestType.SHOUTOUT, this.appDetails, uids);
		com.friendster.api.beans.shoutout.ShoutoutResponse response = (com.friendster.api.beans.shoutout.ShoutoutResponse) requestContext
				.handleRequest();
		return response;
	}

	public com.friendster.api.beans.topscores.GameScoreResponse getTopScores() {
		RequestContext requestContext = new RequestContext(
				RequestType.TOP_SCORES, this.appDetails);
		return (com.friendster.api.beans.topscores.GameScoreResponse) requestContext
				.handleRequest();
	}

	public com.friendster.api.beans.messages.MessageResponse getMessages() {
		RequestContext requestContext = new RequestContext(
				RequestType.MESSAGES, this.appDetails);
		return (com.friendster.api.beans.messages.MessageResponse) requestContext
				.handleRequest();
	}

	public PointsResponse rewardPoints(Integer uid, Integer points) {
		Map<String, Object> requestParameters = new HashMap<String, Object>();
		requestParameters.put("points", String.valueOf(points));
		RequestContext requestContext = new RequestContext(
				RequestType.REWARD_POINTS, this.appDetails, uid, requestParameters);
		return (PointsResponse) requestContext.handleRequest();
	}

	public MessageResponse postMessage(Integer uid, MessageRequest message) {
		RequestContext requestContext = new RequestContext(
				RequestType.MESSAGE_P, this.appDetails, uid,
				message.getRequestMap());
		return (MessageResponse) requestContext.handleRequest();
	}

	public com.friendster.api.beans.message.MessageResponse getMessage(
			Integer cid) {
		RequestContext requestContext = new RequestContext(RequestType.MESSAGE,
				this.appDetails, cid);
		return (com.friendster.api.beans.message.MessageResponse) requestContext
				.handleRequest();
	}

	public GameScoreResponse postScore(int avatarId, int score) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("score", String.valueOf(score));
		RequestContext requestContext = new RequestContext(RequestType.SCORE,
				this.appDetails, avatarId, paramMap);
		return (GameScoreResponse) requestContext.handleRequest();
	}

	public com.friendster.api.beans.ShoutoutResponse postShoutout(
			String shoutOut) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("content", shoutOut);
		RequestContext requestContext = new RequestContext(
				RequestType.SHOUTOUT_P, this.appDetails, paramMap);
		return (com.friendster.api.beans.ShoutoutResponse) requestContext
				.handleRequest();
	}

	public NotificationsResponse postNotification(NotificationRequest request,
			Object... uids) {
		List<Integer> uidList = new ArrayList<Integer>();
		for (Object o : uids) {
			if (o instanceof Integer) {
				uidList.add((Integer) o);
			}
		}
		RequestContext requestContext = new RequestContext(
				RequestType.NOTIFICATION_P, this.appDetails,
				request.getNotificationParams(), uidList);
		return (NotificationsResponse) requestContext.handleRequest();
	}

	public com.friendster.api.beans.WalletResponse getWalletBalance() {
		RequestContext requestContext = new RequestContext(
				RequestType.WALLET_BALANCE, this.appDetails);
		return (com.friendster.api.beans.WalletResponse) requestContext
				.handleRequest();
	}

	public com.friendster.api.beans.wallet.payment.WalletResponse getPaymentRequest(
			PaymentRequest request) {
		RequestContext requestContext = new RequestContext(
				RequestType.WALLET_GET, this.appDetails,
				request.getPaymentParams());
		return (com.friendster.api.beans.wallet.payment.WalletResponse) requestContext
				.handleRequest();
	}

	public com.friendster.api.beans.wallet.commit.WalletResponse commitPaymentRequest(
			String requestToken) {
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("request_token", requestToken);
		RequestContext requestContext = new RequestContext(
				RequestType.WALLET_COMMIT, this.appDetails, paramsMap);
		return (com.friendster.api.beans.wallet.commit.WalletResponse) requestContext
				.handleRequest();

	}

	public com.friendster.api.beans.NewmessagesResponse getNewMessages() {
		RequestContext requestContext = new RequestContext(
				RequestType.NEW_MESSAGES, this.appDetails);
		return (com.friendster.api.beans.NewmessagesResponse) requestContext
				.handleRequest();
	}

	public URI getCallBackUrl(WalletResponse response, String returnURL) {
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("request_token", response.getRequestToken());
		paramsMap.put("return_url", returnURL);
		paramsMap.put("callback_url", response.getRedirectUrl());
		RequestContext requestContext = new RequestContext(
				RequestType.WALLET_CALLBACK, appDetails, paramsMap);
		return (URI) requestContext.handleRequest();
	}
	
	public AssetResponse uploadAsset(String fileName) {
		System.out.println("Attempting To Check if File Exists");
		Map<String, String> paramsMap = new HashMap<String, String>();
		try {
			paramsMap.put("checksum", ReadAsset.getAssetChecksum(fileName));
			paramsMap.put("File", fileName);
		} catch (NoSuchAlgorithmException e) {
			throw new FriendsterAPIException(e);
		} catch (IOException e) {
			throw new FriendsterAPIException(e);
		}
		RequestContext requestContext = new RequestContext(RequestType.ASSET_UPLOAD_INQ, appDetails, paramsMap);
		AssetResponse response = (AssetResponse) requestContext.handleRequest();
		
		if (response.getAssetStatus().equalsIgnoreCase("MISS")) {
			System.out.println("File Not Existing... Uploading....");
			requestContext = new RequestContext(RequestType.ASSET_UPLOAD_PUT, appDetails, paramsMap);
			response = (AssetResponse) requestContext.handleRequest();
		} else {
			System.out.println("File Is Existing Already. No Need to Upload!!!");
		}
		System.out.println("Process Completed");
		return response;
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