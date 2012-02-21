package com.friendster.api.client.request;

import com.friendster.api.client.builders.EndpointIndexBuilder;
import com.friendster.api.client.enums.RequestTypesEnum;

public class SingleUIDRequest extends Request {
	private Integer uid;

	public SingleUIDRequest(RequestTypesEnum requestType,
			AppDetails appDetails, Integer uid) {
		super(requestType, appDetails);
		this.uid = uid;
	}
	
	@Override
	public String getURLEndpoint() {
		return EndpointIndexBuilder.getEndpoint(requestType) + this.uid;
	}
	
}
