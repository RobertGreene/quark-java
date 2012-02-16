package com.friendster.api.client.validators;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.friendster.api.client.builders.EndpointIndexBuilder;
import com.friendster.api.client.digest.FriendsterAPIDigestInterface;
import com.friendster.api.client.request.Request;
import com.friendster.api.client.throwable.FriendsterAPIException;

public class RequestValidator<H, Q> implements RequestValidatorInterface {

	FriendsterAPIDigestInterface hashCreator;
	Request request;

	public RequestValidator(H hashCreator, Q request) {
		if (hashCreator instanceof FriendsterAPIDigestInterface) {
			this.hashCreator = (FriendsterAPIDigestInterface) hashCreator;
		} else {
			throw new FriendsterAPIException();
		}

		if (request instanceof Request) {
			this.request = (Request) request;
		} else {
			throw new FriendsterAPIException();
		}
	}

	public void setHashCreator(H hashCreator) {
		if (hashCreator instanceof FriendsterAPIDigestInterface) {
			this.hashCreator = (FriendsterAPIDigestInterface) hashCreator;
		} else {
			throw new FriendsterAPIException();
		}
	}

	public void setRequest(Q request) {
		if (request instanceof Request) {
			this.request = (Request) request;
		} else {
			throw new FriendsterAPIException();
		}
	}

	public void validateParams() {
		StringBuffer paramsDigest;
		try {
			paramsDigest = new StringBuffer(new URL(EndpointIndexBuilder.getEndpoint(request.getRequestType())).getPath());
		} catch (MalformedURLException e) {
			throw new FriendsterAPIException(e);
		}

		Map<String, String> requestParams = new HashMap<String, String>(
				request.getOtherParams());
		requestParams.put("api_key", request.getApiKey());
		requestParams.put("nonce", this.createSerialNOnce().toString());
		requestParams.put("session_key", request.getSessionKey());

		for (java.util.Map.Entry<String, String> entry : requestParams
				.entrySet()) {
			paramsDigest.append(entry.getKey() + "=" + entry.getValue());
		}

		paramsDigest.append(request.getApiSecret());
		request.setNOnce(requestParams.get("nonce"));
		request.setSignature(this.createSignature(paramsDigest.toString()));
	}

	private String createSignature(String paramsDigest) {
		System.out.println(paramsDigest);
		return this.hashCreator.getHexDigest(paramsDigest);
	}

	private UUID createSerialNOnce() {
		return UUID.randomUUID();
	}

}