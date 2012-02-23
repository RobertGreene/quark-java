package com.friendster.api.client;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;

import com.friendster.api.client.builders.RequestBuilder;
import com.friendster.api.client.digest.ApacheMD5DigestWrapper;
import com.friendster.api.client.enums.RequestMethod;
import com.friendster.api.client.enums.RequestTypesEnum;
import com.friendster.api.client.http.FlexibleHTTPClient;
import com.friendster.api.client.parser.FriendsterAPIRequestParser;
import com.friendster.api.client.parser.FriendsterAPIRequestParserInterface;
import com.friendster.api.client.parser.FriendsterAPIResponseParserInterface;
import com.friendster.api.client.parser.FriendsterAPIXMLResponseParser;
import com.friendster.api.client.request.AppDetails;
import com.friendster.api.client.request.Request;
import com.friendster.api.client.throwable.FriendsterAPIException;
import com.friendster.api.client.validators.RequestValidatorInterface;

public class RequestContext {
	private Request request;
	private List<RequestValidatorInterface> requestValidators;

	public RequestContext(RequestTypesEnum requestType, AppDetails appDetails,
			Object... args) {
		
		this.handleRequestInternal(requestType, appDetails, args);
	}

	public Object handleRequest() {
		this.performValidations();
		FlexibleHTTPClient httpClient = new FlexibleHTTPClient();
		return this.createResponse(httpClient.performRequest(
				this.request.getMethod(),
				this.performRequestParsing(this.request.getMethod()),
				this.request.getRequestParameters()));
	}

	private void handleRequestInternal(RequestTypesEnum requestType,
			AppDetails appDetails, Object... args) {

		this.request = RequestBuilder.buildRequest(requestType, appDetails,
				args);
		this.requestValidators = this.initializeValidators();
	}

	private Object createResponse(HttpEntity httpEntity) {
		FriendsterAPIResponseParserInterface responseParser = new FriendsterAPIXMLResponseParser();
		return responseParser.parseResponse(this.request.getRequestType(), httpEntity);
	}

	@SuppressWarnings("rawtypes")
	private List<RequestValidatorInterface> initializeValidators() {
		requestValidators = new ArrayList<RequestValidatorInterface>();

		try {
			Class targetClass = Class
					.forName("com.friendster.api.client.validators.RequestValidator");
			Constructor targetConstructor = targetClass.getConstructors()[0];
			requestValidators.add((RequestValidatorInterface) targetConstructor
					.newInstance(new ApacheMD5DigestWrapper(), request));
		} catch (ClassNotFoundException e) {
			throw new FriendsterAPIException(e);
		} catch (InstantiationException e) {
			throw new FriendsterAPIException(e);
		} catch (IllegalAccessException e) {
			throw new FriendsterAPIException(e);
		} catch (IllegalArgumentException e) {
			throw new FriendsterAPIException(e);
		} catch (InvocationTargetException e) {
			throw new FriendsterAPIException(e.getTargetException());
		}

		return requestValidators;
	}

	private URI performRequestParsing(RequestMethod requestMethod) {
		// TODO Make this a parameterized thing
		FriendsterAPIRequestParserInterface requestParser = new FriendsterAPIRequestParser(
				this.request);
		return requestParser.parseRequest();
	}

	private void performValidations() {
		for (RequestValidatorInterface validator : requestValidators) {
			validator.validateParams();
		}
	}
}