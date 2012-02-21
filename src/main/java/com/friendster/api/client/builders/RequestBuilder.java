package com.friendster.api.client.builders;

import java.util.ArrayList;
import java.util.List;

import com.friendster.api.client.enums.RequestTypesEnum;
import com.friendster.api.client.request.AppDetails;
import com.friendster.api.client.request.MultipleUIDRequest;
import com.friendster.api.client.request.Request;
import com.friendster.api.client.request.SingleUIDRequest;
import com.friendster.api.client.throwable.FriendsterAPIException;

public class RequestBuilder {

	@SuppressWarnings("unchecked")
	public static Request buildRequest(RequestTypesEnum requestType,
			AppDetails appDetails, Object... args)
			throws FriendsterAPIException {
		List<Integer> applUID = new ArrayList<Integer>();
		for (Object obj : args) {
			if (obj instanceof List) {
				applUID.addAll((List<Integer>) obj);
			} else if (obj instanceof Integer) {
				applUID.add((Integer) obj);
			}
		}
		switch (requestType) {
		case USER:
			return new MultipleUIDRequest(requestType, appDetails,
					applUID);
		case SHOUTOUT:
			return new MultipleUIDRequest(requestType, appDetails,
					(List<Integer>) applUID);
		case APP_FRIENDS:
			return new Request(requestType, appDetails);
		case FRIENDS:
			return new SingleUIDRequest(requestType, appDetails,
					(Integer) args[0]);
		case SHOUTOUT_P:
			return new Request(requestType, appDetails);
		default:
			throw new FriendsterAPIException();
		}
	}
}