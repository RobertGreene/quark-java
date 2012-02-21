package com.friendster.api.client.builders;

import java.util.List;

import com.friendster.api.client.enums.RequestTypesEnum;
import com.friendster.api.client.request.AppDetails;
import com.friendster.api.client.request.MultipleUIDRequest;
import com.friendster.api.client.request.Request;
import com.friendster.api.client.request.SingleUIDRequest;
import com.friendster.api.client.throwable.FriendsterAPIException;

public class RequestBuilder {
	
	@SuppressWarnings("unchecked")
	public static Request buildRequest(RequestTypesEnum requestType, AppDetails appDetails, Object... args) throws FriendsterAPIException {
		switch (requestType) {
		case USER:
			return new MultipleUIDRequest(requestType, appDetails, (List<Integer>) args[0]);
		case SHOUTOUT:
			return new MultipleUIDRequest(requestType, appDetails, (List<Integer>) args[0]);
		case APP_FRIENDS:
			return new Request(requestType, appDetails);
		case FRIENDS:
			return new SingleUIDRequest(requestType, appDetails, (Integer) args[0]);
			//return new MultipleUIDRequest(requestType, appDetails, (List<Integer>) args[0]);
		default:
			throw new FriendsterAPIException();
		}		
	}
}