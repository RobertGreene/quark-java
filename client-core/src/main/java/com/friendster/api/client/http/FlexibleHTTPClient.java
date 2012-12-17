package com.friendster.api.client.http;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
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
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

import com.friendster.api.client.enums.RequestMethod;
import com.friendster.api.client.throwable.FriendsterAPIException;

public class FlexibleHTTPClient {

	public HttpEntity performRequest(RequestMethod requestMethod,
			URI requestURI, Object... args) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpUriRequest httpRequest = null;
		switch (requestMethod) {
		case GET:
			httpRequest = new HttpGet(requestURI);
			break;
		case POST:
			httpRequest = null;
			HttpPost post = new HttpPost(requestURI);
			if (args[0] instanceof Map) {
				@SuppressWarnings({ "unchecked" })
				Map<String, String> formData = new HashMap<String, String>(
						(Map<String, String>) args[0]);
				BasicHttpParams formParams = new BasicHttpParams();
				if (formData.containsKey("File")) {
					MultipartEntity entity = new MultipartEntity();
					try {
						for (Entry<String, String> entry : formData.entrySet()) {
							formParams.setParameter(entry.getKey(),
									entry.getValue());
						}
					} catch (Exception e) {
					}

					entity.addPart("bin",
							new FileBody(new File(formData.get("File"))));
					post.setEntity(entity);
					post.setParams(formParams);
				} else {
					for (Entry<String, String> entry : formData.entrySet()) {
						formParams.setParameter(entry.getKey(),
								entry.getValue());
					}
					post.setParams(formParams);
				}
				httpRequest = post;
			}
			break;
		default:
			break;
		}
		try {
			HttpResponse response = httpClient.execute(httpRequest);
			// byte[] b = new byte[1];
			// InputStream is = response.getEntity().getContent();
			// ArrayList<Byte> a = new ArrayList<Byte>();
			// while (is.read(b) != -1) {
			// a.add(b[0]);
			// }
			// byte[] bb = new byte[a.size()];
			// for (int i = 0; i < a.size(); i++) {
			// bb[i] = a.get(i);
			// }
			// System.out.println(new String(bb).toString());
			return response.getEntity();
		} catch (ClientProtocolException e) {
			throw new FriendsterAPIException(e);
		} catch (IOException e) {
			throw new FriendsterAPIException(e);
		}
	}
}