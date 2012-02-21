package com.friendster.api.client;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import com.friendster.api.client.builders.RequestBuilder;
import com.friendster.api.client.builders.XMLResponseBuilder;
import com.friendster.api.client.digest.ApacheMD5DigestWrapper;
import com.friendster.api.client.enums.RequestMethod;
import com.friendster.api.client.enums.RequestTypesEnum;
import com.friendster.api.client.http.FlexibleHTTPClient;
import com.friendster.api.client.parser.FriendsterAPIGetRequestParser;
import com.friendster.api.client.parser.FriendsterAPIPostRequestParser;
import com.friendster.api.client.parser.FriendsterAPIRequestParserInterface;
import com.friendster.api.client.parser.FriendsterAPIResponseParserInterface;
import com.friendster.api.client.parser.XMLResponseParser;
import com.friendster.api.client.request.AppDetails;
import com.friendster.api.client.request.Request;
import com.friendster.api.client.response.Response;
import com.friendster.api.client.response.ResponseFormat;
import com.friendster.api.client.throwable.FriendsterAPIException;
import com.friendster.api.client.validators.RequestValidatorInterface;

public class RequestContext {
	private Request request;
	private List<RequestValidatorInterface> requestValidators;
	private Response response;

	private static Logger logger = Logger.getLogger(RequestContext.class);

	public RequestContext(RequestTypesEnum requestType, AppDetails appDetails,
			Object... args) {
		this.handleRequestInternal(requestType, appDetails, args);
	}

	public Response handleRequest(Object... args) {
		this.performValidations();
		FlexibleHTTPClient httpClient = new FlexibleHTTPClient();
		URI httpEndpointURI = this.performRequestParsing(this.request
				.getMethod());
		HttpEntity httpEntity = null;

		logger.info("HTTP Request To : \n\t" + httpEndpointURI);

		switch (this.request.getMethod()) {
		case GET:
			httpEntity = httpClient.performGETRequest(httpEndpointURI);
			break;
		case POST:
			httpEntity = httpClient.performPOSTRequest(httpEndpointURI,
					this.request.getRequestParameters());
		default:
			break;
		}
		
		ResponseFormat responseFormat = ResponseFormat.XML;
		for (Object obj : args) {
			if (obj instanceof ResponseFormat) 
				responseFormat = (ResponseFormat) obj;
		}
		
		this.response = this.createResponse(httpEntity, responseFormat);
		return this.response;
	}

	private void handleRequestInternal(RequestTypesEnum requestType,
			AppDetails appDetails, Object... args) {
		this.request = RequestBuilder.buildRequest(requestType, appDetails, args);
		this.requestValidators = this.initializeValidators();
	}

	private Response createResponse(HttpEntity httpEntity, ResponseFormat responseFormat) {
		switch (responseFormat) {
		case XML:
			FriendsterAPIResponseParserInterface responseParser = new XMLResponseParser();
			Document xmlDocument = responseParser.parseResponse(httpEntity);
			return XMLResponseBuilder.buildResponse(xmlDocument);	
		case TEXT:
			logger.error("Unsupported output type: TXT");
			throw new FriendsterAPIException();
		default:
			logger.error("No Output Type Provided");
			throw new FriendsterAPIException();
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

	private URI performRequestParsing(RequestMethod requestMethod) {
		// TODO Make this a parameterized thing
		FriendsterAPIRequestParserInterface requestParser;
		switch (requestMethod) {
		case GET:
			requestParser = new FriendsterAPIGetRequestParser(this.request);
			return requestParser.parseRequest();
		case POST:
			requestParser = new FriendsterAPIGetRequestParser(this.request);
//			requestParser = new FriendsterAPIPostRequestParser(this.request);
			return requestParser.parseRequest();
		default:
			throw new FriendsterAPIException();
		}

	}

	private void performValidations() {
		for (RequestValidatorInterface validator : requestValidators) {
			validator.validateParams();
		}
	}
}