package com.friendster.api.client.parser;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import com.friendster.api.client.request.MultipleUIDRequest;
import com.friendster.api.client.request.Request;
import com.friendster.api.client.throwable.FriendsterAPIException;

public class FriendsterAPIRequestParser implements
		FriendsterAPIRequestParserInterface {
	private Request targetRequest;

	public FriendsterAPIRequestParser(Request sourceRequest) {
		this.targetRequest = sourceRequest;
	}

	public URI parseRequest() {
		try {
			URL requestURL = new URL(targetRequest.getURLEndpoint());
			List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
			requestParams.add(new BasicNameValuePair("api_key", targetRequest
					.getAppDetails().getApiKey()));
			requestParams.add(new BasicNameValuePair("session_key",
					targetRequest.getAppDetails().getSessionKey()));
			requestParams.add(new BasicNameValuePair("nonce", targetRequest
					.getAppDetails().getnOnce()));
			requestParams.add(new BasicNameValuePair("sig", targetRequest
					.getAppDetails().getSignature()));
			
			if (targetRequest instanceof MultipleUIDRequest) {
				requestParams.add(new BasicNameValuePair("uids", ((MultipleUIDRequest) targetRequest).getUIDs()));
			} 

			return URIUtils.createURI(requestURL.getProtocol(),
					requestURL.getHost(), -1, requestURL.getPath(),
					URLEncodedUtils.format(requestParams, "UTF-8"), null);
		} catch (MalformedURLException e) {
			throw new FriendsterAPIException(e);
		} catch (URISyntaxException e) {
			throw new FriendsterAPIException(e);
		}
	}
}