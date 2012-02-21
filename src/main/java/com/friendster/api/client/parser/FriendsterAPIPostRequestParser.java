package com.friendster.api.client.parser;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.friendster.api.client.request.Request;
import com.friendster.api.client.throwable.FriendsterAPIException;

public class FriendsterAPIPostRequestParser implements
		FriendsterAPIRequestParserInterface {

	private Request targetRequest;

	public FriendsterAPIPostRequestParser(Request sourceRequest) {
		this.targetRequest = sourceRequest;
	}

	public URI parseRequest() {
		try {
			this.getConsolidatedParameters();
			return new URI(targetRequest.getURLEndpoint());
		} catch (URISyntaxException e) {
			throw new FriendsterAPIException(e);
		}
	}

	private void getConsolidatedParameters() {
		Map<String, String> consolidatedParameters = new HashMap<String, String>(
				targetRequest.getRequestParameters());
		consolidatedParameters.put("api_key", targetRequest.getAppDetails()
				.getApiKey());
		consolidatedParameters.put("nonce", targetRequest.getAppDetails()
				.getnOnce());
		consolidatedParameters.put("session_key", targetRequest.getAppDetails()
				.getSessionKey());
		consolidatedParameters.put("sig", targetRequest.getAppDetails()
				.getSignature());
		targetRequest.setRequestParameters(consolidatedParameters);
	}
}