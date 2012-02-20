package com.friendster.api.client.http;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

import com.friendster.api.client.throwable.FriendsterAPIException;

// TODO This class is to be thrown out once a better implementation can be made.

public class FlexibleHTTPClient {
	private Logger logger = Logger.getLogger(FlexibleHTTPClient.class);

	public HttpEntity performHTTPRequest(URI requestURI) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpUriRequest httpget = new HttpGet(requestURI);
		try {
			HttpResponse response = httpclient.execute(httpget);
			logger.info("response>." + response.toString());
			return response.getEntity();
		} catch (ClientProtocolException e) {
			throw new FriendsterAPIException(e);
		} catch (IOException e) {
			throw new FriendsterAPIException(e);
		}
	}
}