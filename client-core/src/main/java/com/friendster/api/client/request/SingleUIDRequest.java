package com.friendster.api.client.request;

import com.friendster.api.client.builders.EndpointIndexBuilder;
import com.friendster.api.client.enums.RequestType;

public class SingleUIDRequest extends Request {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8357463469939386301L;
	private Integer uid;

	public SingleUIDRequest(RequestType requestType,
			FriendsterPCPAppInfo appDetails, Integer uid) {
		super(requestType, appDetails);
		this.uid = uid;
	}
	
	@Override
	public String getURLEndpoint() {
		return EndpointIndexBuilder.getEndpoint(requestType) + this.uid;
	}
	
}
