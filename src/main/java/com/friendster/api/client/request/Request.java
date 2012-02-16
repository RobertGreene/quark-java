package com.friendster.api.client.request;

import java.util.Map;

import com.friendster.api.client.enums.RequestTypesEnum;

/**
 * Base Request Class for Friendster API Client Friendster Inc.
 * 
 * @author Paulo Mendoza & Chiqui Mercene
 * @since Feb 15, 2012
 * 
 */

public class Request implements RequestInterface {
	private RequestTypesEnum requestType;
	private String apiKey;
	private String apiSecret;
	private String sessionKey;
	private String nOnce;
	private String signature;

	private Map<String, String> otherParams;

	public Request(RequestTypesEnum requestType, final String apiKey,
			final String apiSecret, final String sessionKey,
			final Map<String, String> requestParams) {
		this.requestType = (requestType);
		this.apiKey = apiKey;
		this.apiSecret = apiSecret;
		this.sessionKey = sessionKey;
		this.otherParams = requestParams;
	}

	public String getApiKey() {
		return this.apiKey;
	}

	public String getApiSecret() {
		return apiSecret;
	}

	public String getSessionKey() {
		return this.sessionKey;
	}

	public String getnOnce() {
		return nOnce;
	}

	public String getSignature() {
		return signature;
	}

	public Map<String, String> getOtherParams() {
		return otherParams;
	}

	public void addParams(String key, String value) {
		this.otherParams.put(key, value);
	}

	public void setNOnce(String nOnce) {
		this.nOnce = nOnce;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public RequestTypesEnum getRequestType() {
		return requestType;
	}
}