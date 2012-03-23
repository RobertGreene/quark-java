package com.friendster.api.client.http;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.log4j.Logger;

import com.friendster.api.client.enums.RequestMethod;
import com.friendster.api.client.throwable.FriendsterAPIException;

public class FlexibleHTTPClient {
	private Logger logger = Logger.getLogger(FlexibleHTTPClient.class);

	public HttpEntity performRequest(RequestMethod requestMethod,
			URI requestURI, Object... args) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpUriRequest httpRequest = null;
		switch (requestMethod) {
		case GET:
			httpRequest = new HttpGet(requestURI);
			break;
		case POST:
			httpRequest = new HttpPost(requestURI);
			if (args[0] instanceof Map) {
				@SuppressWarnings({ "unchecked" })
				Map<String, String> formData = new HashMap<String, String>(
						(Map<String, String>) args[0]);
				BasicHttpParams formParams = new BasicHttpParams();
				for (Entry<String, String> entry : formData.entrySet()) {
					formParams.setParameter(entry.getKey(), entry.getValue());
				}
				httpRequest.setParams(formParams);
			}
			break;
		default:
			break;
		}
		logger.debug("Request URI: " + httpRequest.getURI());
		try {
			HttpResponse response = httpClient.execute(httpRequest);
			logger.info("Received Response : \n\t" + response.toString());
			return response.getEntity();
		} catch (ClientProtocolException e) {
			throw new FriendsterAPIException(e);
		} catch (IOException e) {
			throw new FriendsterAPIException(e);
		}
	}
}