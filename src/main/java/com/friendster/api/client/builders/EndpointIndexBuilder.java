package com.friendster.api.client.builders;

import java.util.HashMap;
import java.util.Map;

import com.friendster.api.client.enums.RequestTypesEnum;
import com.friendster.api.client.throwable.FriendsterAPIException;

public class EndpointIndexBuilder {
	private static Map<RequestTypesEnum, String> endpointIndex;
	private static Map<RequestTypesEnum, String> methodIndex;
	private static final String BASE_URL = "http://api.friendster.com/v1/";

	// TODO Change this to an external configuration file later
	static {
		buildEndpointIndex();
		buildMethodIndex();
	}

	private static void buildEndpointIndex() {
		endpointIndex = new HashMap<RequestTypesEnum, String>();
		endpointIndex.put(RequestTypesEnum.USER, BASE_URL + "user");
		endpointIndex.put(RequestTypesEnum.SHOUTOUT, BASE_URL + "shoutout");
		endpointIndex.put(RequestTypesEnum.APP_FRIENDS, BASE_URL + "application/friends");
		endpointIndex.put(RequestTypesEnum.FRIENDS, BASE_URL + "friends/");
		endpointIndex.put(RequestTypesEnum.SCORE, BASE_URL + "score/");
		endpointIndex.put(RequestTypesEnum.TOP_SCORES, BASE_URL + "score");
	}
	
	private static void buildMethodIndex() {
		methodIndex = new HashMap<RequestTypesEnum, String>();
		methodIndex.put(RequestTypesEnum.USER, "GET");
		methodIndex.put(RequestTypesEnum.SHOUTOUT, "GET");
		methodIndex.put(RequestTypesEnum.APP_FRIENDS, "GET");
		methodIndex.put(RequestTypesEnum.FRIENDS, "GET");
		methodIndex.put(RequestTypesEnum.SCORE, "POST");
		methodIndex.put(RequestTypesEnum.TOP_SCORES, "GET");

	}
	
	public static String getEndpoint(RequestTypesEnum requestType) {
		if (endpointIndex.get(requestType) == null)
			throw new FriendsterAPIException();
		else
			return endpointIndex.get(requestType);
	}
	
	public static String getMethod(RequestTypesEnum requestType) {
		if (methodIndex.get(requestType) == null)
			throw new FriendsterAPIException();
		else
			return methodIndex.get(requestType);
	}
}