package com.friendster.api.client;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;

import com.friendster.api.client.builders.RequestBuilder;
import com.friendster.api.client.digest.ApacheMD5DigestWrapper;
import com.friendster.api.client.enums.RequestMethod;
import com.friendster.api.client.enums.RequestType;
import com.friendster.api.client.http.FlexibleHTTPClient;
import com.friendster.api.client.parser.FriendsterAPIRequestParser;
import com.friendster.api.client.parser.FriendsterAPIRequestParserInterface;
import com.friendster.api.client.parser.FriendsterAPIResponseParserInterface;
import com.friendster.api.client.parser.FriendsterAPIXMLResponseParser;
import com.friendster.api.client.request.FriendsterPCPAppInfo;
import com.friendster.api.client.request.Request;
import com.friendster.api.client.throwable.FriendsterAPIException;
import com.friendster.api.client.throwable.FriendsterAPIServiceException;
import com.friendster.api.client.validators.RequestValidatorInterface;
import com.friendster.api.v1.error.ErrorResponse;

public class RequestContext {
	private Request request;
	private List<RequestValidatorInterface> requestValidators;

	public RequestContext(RequestType requestType,
			FriendsterPCPAppInfo appDetails, Object... args) {

		this.handleRequestInternal(requestType, appDetails, args);
	}

	public Object handleRequest() {
		this.performValidations();
		FlexibleHTTPClient httpClient = new FlexibleHTTPClient();

		try {
			switch (this.request.getRequestType()) {
			case WALLET_CALLBACK:
				HttpGet httpRequest = new HttpGet(
						this.performRequestParsing(RequestMethod.GET));
				return httpRequest.getURI();
			default:
				Object response = this.createResponse(httpClient
						.performRequest(this.request.getMethod(),
								this.performRequestParsing(this.request
										.getMethod()), this.request
										.getRequestParameters()));
				return response;
			}
		} catch (FriendsterAPIException e) {
			ErrorResponse errorResponse = this.performErrorParsing();
			throw new FriendsterAPIServiceException(
					errorResponse.getErrorCode(), errorResponse.getErrorMsg());
		}
	}

	private Object createResponse(HttpEntity httpEntity) {
		FriendsterAPIResponseParserInterface responseParser = new FriendsterAPIXMLResponseParser();
		return responseParser.parseResponse(this.request.getRequestType(),
				httpEntity);
	}
	
	private void handleRequestInternal(RequestType requestType,
			FriendsterPCPAppInfo appDetails, Object... args) {
		this.request = RequestBuilder.buildRequest(requestType, appDetails,
				args);
		this.requestValidators = this.initializeValidators();
	}
	
	private URI performRequestParsing(RequestMethod requestMethod) {
		FriendsterAPIRequestParserInterface requestParser = new FriendsterAPIRequestParser(
				this.request);
		return requestParser.parseRequest();
	}
	
	private ErrorResponse performErrorParsing() {
		FlexibleHTTPClient httpClient = new FlexibleHTTPClient();
		FriendsterAPIXMLResponseParser responseParser = new FriendsterAPIXMLResponseParser();
		return responseParser.parsePossibleError(httpClient.performRequest(
				this.request.getMethod(),
				this.performRequestParsing(this.request.getMethod()),
				this.request.getRequestParameters()));
	}
	
	private void performValidations() {
		for (RequestValidatorInterface validator : requestValidators) {
			validator.validateParams();
		}
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

}