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

import com.friendster.api.client.throwable.FriendsterAPIException;

// TODO This class is to be thrown out once a better implementation can be made.

public class FlexibleHTTPClient {
	private Logger logger = Logger.getLogger(FlexibleHTTPClient.class);

	public HttpEntity performGETRequest(URI requestURI) {
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
	
	public HttpEntity performPOSTRequest(URI requestURI, Object... args) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpUriRequest httppost = new HttpPost(requestURI);
		
		if (args[0] instanceof Map) {
			@SuppressWarnings({ "unchecked" })
			Map<String, String> formData = new HashMap<String, String>((Map<String, String>)args[0]);
			BasicHttpParams formParams = new BasicHttpParams();
			
			for(Entry<String, String> entry : formData.entrySet()) {
				formParams.setParameter(entry.getKey(), entry.getValue());
			}
			httppost.setParams(formParams);
			
			logger.debug("API Key: \n\t" + httppost.getParams().getParameter("api_key"));
			logger.debug("Session Key: \n\t" + httppost.getParams().getParameter("session_key"));
			logger.debug("N-Once: \n\t" + httppost.getParams().getParameter("nonce"));
			logger.debug("Signature: \n\t" + httppost.getParams().getParameter("sig"));
			logger.debug("Content: \n\t" + httppost.getParams().getParameter("content"));
			
		}
		
		try {
			HttpResponse response = httpclient.execute(httppost);
			logger.info("Received Response : \n\t" + response.toString());
			return response.getEntity();
		} catch (ClientProtocolException e) {
			throw new FriendsterAPIException(e);
		} catch (IOException e) {
			throw new FriendsterAPIException(e);
		}
	}
}