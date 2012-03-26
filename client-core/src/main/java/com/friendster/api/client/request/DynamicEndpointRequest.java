package com.friendster.api.client.request;

import java.util.Map;

import com.friendster.api.client.enums.RequestType;

public class DynamicEndpointRequest extends Request {
	private String endpointURL;

	public DynamicEndpointRequest(RequestType requestType,
			FriendsterPCPAppInfo appDetails) {
		super(requestType, appDetails);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3511061555934270097L;
	
	@SuppressWarnings("unchecked")
	@Override
	public void setRequestParameters(Object... args) {
		if ((args[0] instanceof Map) && (otherParams == null)) {
			otherParams = (Map<String, String>) args[0];
		} else if (args[0] instanceof Map) {
			this.otherParams.putAll((Map<String, String>) args[0]);
		}
		this.endpointURL = this.otherParams.remove("callback_url");
	}

	@Override
	public String getURLEndpoint() {
		return this.endpointURL;
	}

}