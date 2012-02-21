package com.friendster.api.client.builders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Map<String, String> requestParameters = new HashMap<String, String>();
		for (Object obj : args) {
			if (obj instanceof List) {
				applUID.addAll((List<Integer>) obj);
			} else if (obj instanceof Integer) {
				System.out.println("added" + obj);
				applUID.add((Integer) obj);
			} else if (obj instanceof Map) {
				requestParameters.putAll((Map<String, String>) obj);
			}
		}

		Request request = null;
		switch (requestType) {
		case USER:
			request = new MultipleUIDRequest(requestType, appDetails, applUID);

			break;
		case SHOUTOUT:
			request = new MultipleUIDRequest(requestType, appDetails, applUID);
			break;
		case APP_FRIENDS:
			request = new Request(requestType, appDetails);
			break;
		case FRIENDS:
			request = new SingleUIDRequest(requestType, appDetails,
					(Integer) args[0]);
			break;
		case SHOUTOUT_P:
			request = new Request(requestType, appDetails);
			break;
		case MESSAGES:
			request = new Request(requestType, appDetails);
			break;
			
		default:
			break;
		}

		if (request != null) {
			request.setRequestParameters(requestParameters);
		} else {
			throw new FriendsterAPIException();
		}

		return request;
	}
}