package com.friendster.api.client.builders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.friendster.api.client.enums.RequestType;
import com.friendster.api.client.request.DynamicEndpointRequest;
import com.friendster.api.client.request.FriendsterPCPAppInfo;
import com.friendster.api.client.request.MultipleUIDRequest;
import com.friendster.api.client.request.Request;
import com.friendster.api.client.request.SingleUIDRequest;
import com.friendster.api.client.throwable.FriendsterAPIException;

public class RequestBuilder {

	@SuppressWarnings("unchecked")
	public static Request buildRequest(RequestType requestType,
			FriendsterPCPAppInfo appDetails, Object... args)
			throws FriendsterAPIException {

		List<Integer> applUID = new ArrayList<Integer>();
		Map<String, String> requestParameters = new HashMap<String, String>();
		for (Object obj : args) {
			if (obj instanceof List) {
				applUID.addAll((List<Integer>) obj);
			} else if (obj instanceof Integer) {
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
		case MESSAGE:
			request = new SingleUIDRequest(requestType, appDetails,
					(Integer) args[0]);
			break;
		case MESSAGE_P:
			request = new SingleUIDRequest(requestType, appDetails, (Integer) args[0]);
		case TOP_SCORES:
			request = new Request(requestType, appDetails);
			break;
		case SCORE:
			request = new SingleUIDRequest(requestType, appDetails, (Integer) args[0]);
			break;
		case NOTIFICATION_P:
			request = new MultipleUIDRequest(requestType, appDetails, applUID);
			break;
		case WALLET_BALANCE:
			request = new Request(requestType, appDetails);
			break;
		case WALLET_COMMIT:
			request = new Request(requestType, appDetails);
			break;
		case WALL_RICHPOST:
			request = new Request(requestType, appDetails);
			break;
		case WALLET_GET:
			request = new Request(requestType, appDetails);
			break;
		case WALLET_CALLBACK:
			request = new DynamicEndpointRequest(requestType, appDetails);
			break;
		case NEW_MESSAGES:
			request = new Request(requestType, appDetails);
			break;
		case REWARD_POINTS:
			request = new SingleUIDRequest(requestType, appDetails, (Integer) args[0]);
		case ASSET_UPLOAD_INQ:
			request = new Request(requestType, appDetails);
		case ASSET_UPLOAD_PUT:
			request = new Request(requestType, appDetails);
		case APPLICATION_GUILDS:
			request = new Request(requestType, appDetails);
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
