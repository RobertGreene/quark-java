package com.friendster.api.client.http;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.friendster.api.client.builders.EndpointIndexBuilder;
import com.friendster.api.client.request.Request;
import com.friendster.api.client.throwable.FriendsterAPIException;

// TODO This class is to be thrown out once a better implementation can be made.

public class FlexibleHTTPClient {
	private Request request;
	private URL requestURL;

	public FlexibleHTTPClient(Request request) {
		this.request = request;
	}

	public void performHTTPRequest() {
		try {
			this.requestURL = new URL(EndpointIndexBuilder.getEndpoint(request.getRequestType()));
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(this.uriBuilder(request));
			HttpResponse response = httpclient.execute(httpget);
			System.out.println(httpget.getURI());
			HttpEntity entity = response.getEntity();
			if (entity != null) {
			    long len = entity.getContentLength();
			    if (len != -1 && len < 2048) {
			        System.out.println(EntityUtils.toString(entity));
			    } else {
			    }
			}
		} catch (MalformedURLException e) {
			throw new FriendsterAPIException(e);
		} catch (URISyntaxException e) {
			throw new FriendsterAPIException(e);
		} catch (ClientProtocolException e) {
			throw new FriendsterAPIException(e);
		} catch (IOException e) {
			throw new FriendsterAPIException(e);
		}
	}

	public URI uriBuilder(Request request) throws URISyntaxException {
		List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
		requestParams.add(new BasicNameValuePair("api_key", request.getApiKey()));
		requestParams.add(new BasicNameValuePair("session_key", request.getSessionKey()));
		requestParams.add(new BasicNameValuePair("uids", request.getOtherParams().get("uids")));
		requestParams.add(new BasicNameValuePair("nonce", request.getnOnce()));
		requestParams.add(new BasicNameValuePair("sig", request.getSignature()));
		return URIUtils.createURI(requestURL.getProtocol(), requestURL.getHost(), -1, requestURL.getPath(),
				URLEncodedUtils.format(requestParams, "UTF-8"), null);
	}
}
