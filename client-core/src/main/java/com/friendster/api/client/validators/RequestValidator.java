package com.friendster.api.client.validators;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TreeMap;

import com.friendster.api.client.digest.FriendsterAPIDigestInterface;
import com.friendster.api.client.request.Request;
import com.friendster.api.client.throwable.FriendsterAPIException;

public class RequestValidator<H, Q> implements RequestValidatorInterface {

	private FriendsterAPIDigestInterface hashCreator;
	private Request request;

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
			paramsDigest = new StringBuffer(new URL(this.request.getURLEndpoint()).getPath());
		} catch (MalformedURLException e) {
			throw new FriendsterAPIException(e);
		}

		Map<String, String> requestParams = new TreeMap<String, String>(
				request.getRequestParameters());
		
		requestParams.put("api_key", request.getAppDetails().getApiKey());
		requestParams.put("nonce", this.createSerialNOnce().toString());
		requestParams.put("session_key", request.getAppDetails().getSessionKey());

		for (java.util.Map.Entry<String, String> entry : requestParams.entrySet()) {
			paramsDigest.append(entry.getKey() + "=" + entry.getValue());
		}

		paramsDigest.append(request.getAppDetails().getApiSecret());
		request.getAppDetails().setnOnce(requestParams.get("nonce"));
		request.getAppDetails().setSignature(this.createSignature(paramsDigest.toString()));
	}

	private String createSignature(String paramsDigest) {
		return this.hashCreator.getHexDigest(paramsDigest);
	}

	private String createSerialNOnce() {
		BigDecimal decimalNOnce = new BigDecimal(new GregorianCalendar().getTimeInMillis());
		decimalNOnce = decimalNOnce.divide(new BigDecimal(1000));
		DecimalFormat nOnceFormat = new DecimalFormat("#.000");
		
		return String.valueOf(nOnceFormat.format(decimalNOnce));
	}
}