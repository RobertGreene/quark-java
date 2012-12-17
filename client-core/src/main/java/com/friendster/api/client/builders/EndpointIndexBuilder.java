package com.friendster.api.client.builders;

import java.util.HashMap;
import java.util.Map;

import com.friendster.api.client.enums.RequestMethod;
import com.friendster.api.client.enums.RequestType;
import com.friendster.api.client.throwable.FriendsterAPIException;

public class EndpointIndexBuilder {
	private static Map<RequestType, String> endpointIndex;
	private static Map<RequestType, RequestMethod> methodIndex;
	private static final String BASE_URL = "http://api.friendster.com/v1/";

	static {
		buildEndpointIndex();
		buildMethodIndex();
	}

	private static void buildEndpointIndex() {
		endpointIndex = new HashMap<RequestType, String>();
		endpointIndex.put(RequestType.USER, BASE_URL + "user");
		endpointIndex.put(RequestType.APP_FRIENDS, BASE_URL
				+ "application/friends");
		endpointIndex.put(RequestType.FRIENDS, BASE_URL + "friends/");
		endpointIndex.put(RequestType.SHOUTOUT, BASE_URL + "shoutout");
		endpointIndex.put(RequestType.MESSAGES, BASE_URL + "messages");
		endpointIndex.put(RequestType.MESSAGE_P, BASE_URL + "messages");
		endpointIndex.put(RequestType.MESSAGE, BASE_URL + "message/");

		// messages/destroy/:mid
		endpointIndex.put(RequestType.SHOUTOUT_P, BASE_URL + "shoutout");
		endpointIndex
				.put(RequestType.NOTIFICATION_P, BASE_URL + "notification");
		endpointIndex.put(RequestType.SCORE, BASE_URL + "score/");
		endpointIndex.put(RequestType.TOP_SCORES, BASE_URL + "score");

		// sessions/destroy
		endpointIndex.put(RequestType.WALLET_GET, BASE_URL + "wallet/payment");
		endpointIndex
				.put(RequestType.WALLET_COMMIT, BASE_URL + "wallet/commit");
		endpointIndex.put(RequestType.WALLET_BALANCE, BASE_URL + "wallet/balance");
		
		endpointIndex.put(RequestType.WALLET_COMMIT, BASE_URL + "wallet/commit");
		
		endpointIndex.put(RequestType.NEW_MESSAGES, BASE_URL + "newmessages");
		endpointIndex.put(RequestType.REWARD_POINTS, BASE_URL + "points/");
		endpointIndex.put(RequestType.ASSET_UPLOAD_INQ, BASE_URL + "assets/");
		endpointIndex.put(RequestType.ASSET_UPLOAD_PUT, BASE_URL + "assets/");
		endpointIndex.put(RequestType.APPLICATION_GUILDS, BASE_URL + "application_guilds");
		
	}

	private static void buildMethodIndex() {
		methodIndex = new HashMap<RequestType, RequestMethod>();
		methodIndex.put(RequestType.USER, RequestMethod.GET);
		methodIndex.put(RequestType.SHOUTOUT, RequestMethod.GET);
		methodIndex.put(RequestType.APP_FRIENDS, RequestMethod.GET);
		methodIndex.put(RequestType.FRIENDS, RequestMethod.GET);
		methodIndex.put(RequestType.SCORE, RequestMethod.POST);
		methodIndex.put(RequestType.MESSAGE, RequestMethod.GET);
		methodIndex.put(RequestType.TOP_SCORES, RequestMethod.GET);
		methodIndex.put(RequestType.SHOUTOUT_P, RequestMethod.POST);
		methodIndex.put(RequestType.MESSAGES, RequestMethod.GET);
		methodIndex.put(RequestType.MESSAGE_P, RequestMethod.POST);
		methodIndex.put(RequestType.NOTIFICATION_P, RequestMethod.POST);
		methodIndex.put(RequestType.WALLET_BALANCE, RequestMethod.GET);
		methodIndex.put(RequestType.WALLET_COMMIT, RequestMethod.POST);
		methodIndex.put(RequestType.WALLET_GET, RequestMethod.POST);
		methodIndex.put(RequestType.WALLET_CALLBACK, RequestMethod.GET);
		methodIndex.put(RequestType.NEW_MESSAGES, RequestMethod.GET);
		methodIndex.put(RequestType.REWARD_POINTS, RequestMethod.POST);
		methodIndex.put(RequestType.ASSET_UPLOAD_INQ, RequestMethod.GET);
		methodIndex.put(RequestType.ASSET_UPLOAD_PUT, RequestMethod.POST);
		methodIndex.put(RequestType.APPLICATION_GUILDS, RequestMethod.GET);
	}

	public static String getEndpoint(RequestType requestType) {
		if (endpointIndex.get(requestType) == null)
			throw new FriendsterAPIException();
		else
			return endpointIndex.get(requestType);
	}

	public static RequestMethod getMethod(RequestType requestType) {
		if (methodIndex.get(requestType) == null)
			throw new FriendsterAPIException();
		else
			return methodIndex.get(requestType);
	}
}