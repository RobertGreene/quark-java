package com.friendster.api.client.builders;

import com.friendster.api.client.enums.RequestTypesEnum;
import com.friendster.api.client.request.AppDetails;
import com.friendster.api.client.request.AppFriendRequest;
import com.friendster.api.client.request.Request;
import com.friendster.api.client.request.ShoutoutRequest;
import com.friendster.api.client.request.UserRequest;
import com.friendster.api.client.throwable.FriendsterAPIException;

public class RequestBuilder {
	
	public static Request buildRequest(RequestTypesEnum requestType, AppDetails appDetails) throws FriendsterAPIException {
		switch (requestType) {
		case USER:
			return new UserRequest(requestType, appDetails);
		case APP_FRIENDS:
			return new AppFriendRequest(requestType, appDetails);
		case SHOUTOUT:
			return new ShoutoutRequest(requestType, appDetails);
		default:
			throw new FriendsterAPIException();
		}		
	}
}