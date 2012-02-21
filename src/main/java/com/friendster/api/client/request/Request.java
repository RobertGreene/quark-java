package com.friendster.api.client.request;

import java.util.Map;

import com.friendster.api.client.builders.EndpointIndexBuilder;
import com.friendster.api.client.enums.RequestMethod;
import com.friendster.api.client.enums.RequestTypesEnum;

/**
 * Base Request Class for Friendster API Client Friendster Inc.
 * 
 * @author Paulo Mendoza & Chiqui Mercene
 * @since Feb 15, 2012
 * 
 */

public class Request implements RequestInterface {
	protected final RequestTypesEnum requestType;
	private final AppDetails appDetails;
	protected Map<String, String> otherParams;

	public Request(final RequestTypesEnum requestType,
			final AppDetails appDetails) {
		this.requestType = requestType;
		this.appDetails = appDetails;
	}

	public RequestTypesEnum getRequestType() {
		return this.requestType;
	}

	public AppDetails getAppDetails() {
		return this.appDetails;
	}

	public String getRequestParameter(String parameterKey) {
		return this.otherParams.get(parameterKey);
	}

	public Map<String, String> getRequestParameters() {
		return this.otherParams;
	}

	@SuppressWarnings("unchecked")
	public void setRequestParameters(Object... args) {
		if ((args[0] instanceof Map) && (otherParams == null)) {
			otherParams = (Map<String, String>) args[0];
		} else if (args[0] instanceof Map) {
			this.otherParams.putAll((Map<String, String>) args[0]);
		}
	}

	public String getURLEndpoint() {
		return EndpointIndexBuilder.getEndpoint(requestType);
	}

	public RequestMethod getMethod() {
		return EndpointIndexBuilder.getMethod(requestType);
	}
}