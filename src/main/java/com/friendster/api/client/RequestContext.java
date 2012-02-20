package com.friendster.api.client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import com.friendster.api.client.builders.EvaluatingRequestBuilder;
import com.friendster.api.client.builders.XMLResponseBuilder;
import com.friendster.api.client.digest.ApacheMD5DigestWrapper;
import com.friendster.api.client.http.FlexibleHTTPClient;
import com.friendster.api.client.parser.FriendsterAPIRequestParser;
import com.friendster.api.client.parser.FriendsterAPIRequestParserInterface;
import com.friendster.api.client.parser.FriendsterAPIResponseParserInterface;
import com.friendster.api.client.parser.XMLResponseParser;
import com.friendster.api.client.request.Request;
import com.friendster.api.client.response.Response;
import com.friendster.api.client.throwable.FriendsterAPIException;
import com.friendster.api.client.validators.RequestValidatorInterface;

public class RequestContext {
	private Request request;
	private List<RequestValidatorInterface> requestValidators;
	private Response response;

	private static Logger logger = Logger.getLogger(RequestContext.class);
	
	public RequestContext(Map<String, String> requestParameters) {
		this.handleRequestInternal(requestParameters);
	}

	public Response handleRequest() {
		this.performValidations();
		FlexibleHTTPClient httpClient = new FlexibleHTTPClient();
		URI httpEndpointURI = this.performRequestParsing();
		
		logger.info("httpEndpointURI-----------" + httpEndpointURI);
		
		HttpEntity httpEntity = httpClient.performHTTPRequest(httpEndpointURI);
		this.response = this.createResponse(httpEntity);
		
		return this.response;
	}
	
	private Response createResponse(HttpEntity httpEntity) {
		FriendsterAPIResponseParserInterface responseParser = new XMLResponseParser();
		Document xmlDocument = responseParser.parseResponse(httpEntity);
		return XMLResponseBuilder.buildResponse(xmlDocument);
	}

	@SuppressWarnings("unchecked")
	private void handleRequestInternal(Object... args) {
		this.request = this.initializeRequest((Map<String, String>) args[0]);
		this.requestValidators = this.initializeValidators();
	}

	private List<RequestValidatorInterface> initializeValidators() {

		requestValidators = new ArrayList<RequestValidatorInterface>();

		try {
			Class targetClass = Class.forName("com.friendster.api.client.validators.RequestValidator");
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
			throw new FriendsterAPIException(e);
		}
		return requestValidators;
	}

	private Request initializeRequest(Map<String, String> requestParams) {
		return EvaluatingRequestBuilder.createRequest(requestParams);
	}
	
	private URI performRequestParsing() {
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