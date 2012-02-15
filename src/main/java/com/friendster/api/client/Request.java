package com.friendster.api.client;

import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.friendster.api.client.throwable.FriendsterAPIError;
import com.friendster.api.client.throwable.FriendsterAPIException;

/**
 * Base Request Class for Friendster API Client Friendster Inc.
 * 
 * @author Paulo Mendoza & Chiqui Mercene
 * @since Feb 15, 2012
 * 
 */

public class Request implements RequestInterface {
	private URL endpoint;
	private String apiKey;
	private String apiSecret;
	private String sessionKey;
	private UUID nOnce;
	private byte[] signature;

	private Map<String, String> otherParams;

	public Request(final URL endpoint, final String apiKey, final String apiSecret,
			final String sessionKey) throws FriendsterAPIError {
		
		if ((endpoint == null) || (apiKey == null) || (apiSecret == null) || (sessionKey == null)) {
			throw new FriendsterAPIError();
		}
		this.endpoint = endpoint;
		this.apiKey = apiKey;
		this.apiSecret = apiSecret;
		this.sessionKey = sessionKey;
		this.nOnce = this.generateNOnce();
		this.otherParams = new HashMap<String, String>();
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

	public UUID getnOnce() {
		return nOnce;
	}

	public byte[] getSignature() {
		return signature;
	}

	public Map<String, String> getOtherParams() {
		return otherParams;
	}

	public void addParams(String key, String value) {
		this.otherParams.put(key, value);
	}

	protected UUID generateNOnce() {
		return UUID.randomUUID();
	}

	public byte[] generateSignature() throws FriendsterAPIException {
		StringBuffer digestParams = new StringBuffer(endpoint.getPath());

		Map<String, String> requestParams = new HashMap<String, String>(
				otherParams);
		requestParams.put("api_key", apiKey);
		requestParams.put("nonce", nOnce.toString());
		requestParams.put("session_key", sessionKey);

		for (java.util.Map.Entry<String, String> entry : requestParams
				.entrySet()) {
			digestParams.append(entry.getKey() + "=" + entry.getValue() + "&");
		}

		digestParams.append(apiSecret);

		MessageDigest digest;
		try {
			digest = java.security.MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new FriendsterAPIException(e);
		}
		digest.update(digestParams.toString().getBytes());
		byte[] signature = digest.digest();
		return signature;
	}
}