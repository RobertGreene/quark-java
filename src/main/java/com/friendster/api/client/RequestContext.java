package com.friendster.api.client;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import com.friendster.api.client.builders.RequestBuilder;
import com.friendster.api.client.builders.XMLResponseBuilder;
import com.friendster.api.client.digest.ApacheMD5DigestWrapper;
import com.friendster.api.client.enums.RequestTypesEnum;
import com.friendster.api.client.http.FlexibleHTTPClient;
import com.friendster.api.client.parser.FriendsterAPIRequestParser;
import com.friendster.api.client.parser.FriendsterAPIRequestParserInterface;
import com.friendster.api.client.parser.FriendsterAPIResponseParserInterface;
import com.friendster.api.client.parser.XMLResponseParser;
import com.friendster.api.client.request.AppDetails;
import com.friendster.api.client.request.Request;
import com.friendster.api.client.response.Response;
import com.friendster.api.client.throwable.FriendsterAPIException;
import com.friendster.api.client.validators.RequestValidatorInterface;

public class RequestContext {
	private Request request;
	private List<RequestValidatorInterface> requestValidators;
	private Response response;

	private static Logger logger = Logger.getLogger(RequestContext.class);

	public RequestContext(RequestTypesEnum requestType, AppDetails appDetails,
			Map<String, String> requestParameters, Object... args) {
		
		if (args[0] instanceof List) {
			logger.debug("Received Request with Multiple UIDS");
			this.request = RequestBuilder.buildRequest(requestType, appDetails, args[0]);
		} else if (args[0] instanceof Integer) {
			this.request = RequestBuilder.buildRequest(requestType, appDetails, args[0]);
		} else {
			this.request = RequestBuilder.buildRequest(requestType, appDetails);	
		}
		
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

	private void handleRequestInternal(Object... args) {
		this.request.setRequestParameters(args[0]);
		this.requestValidators = this.initializeValidators();
	}
	
	private Response createResponse(HttpEntity httpEntity) {
		FriendsterAPIResponseParserInterface responseParser = new XMLResponseParser();
		Document xmlDocument = responseParser.parseResponse(httpEntity);
		return XMLResponseBuilder.buildResponse(xmlDocument);
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