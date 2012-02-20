package com.friendster.api.client.request;

import com.friendster.api.client.enums.RequestTypesEnum;

public class UserRequest extends Request {

	public UserRequest(RequestTypesEnum requestType, AppDetails appDetails) {
		super(requestType, appDetails);
	}

}
