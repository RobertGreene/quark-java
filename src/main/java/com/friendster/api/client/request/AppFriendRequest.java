package com.friendster.api.client.request;

import com.friendster.api.client.enums.RequestTypesEnum;

public class AppFriendRequest extends Request {

	public AppFriendRequest(RequestTypesEnum requestType, AppDetails appDetails) {
		super(requestType, appDetails);
	}

}
