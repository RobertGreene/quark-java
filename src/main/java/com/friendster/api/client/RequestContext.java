package com.friendster.api.client;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.friendster.api.client.builders.EvaluatingRequestBuilder;
import com.friendster.api.client.digest.ApacheMD5DigestWrapper;
import com.friendster.api.client.http.FlexibleHTTPClient;
import com.friendster.api.client.request.Request;
import com.friendster.api.client.response.Response;
import com.friendster.api.client.throwable.FriendsterAPIException;
import com.friendster.api.client.validators.RequestValidatorInterface;

public class RequestContext {
	private Request request;
	private List<RequestValidatorInterface> requestValidators;
	private Response response;

	public RequestContext(Map<String, String> requestParameters) {
		this.handleRequestInternal(requestParameters);
	}

	private void handleRequestInternal(Object... args) {
		this.request = this.initializeRequest((Map<String, String>) args[0]);
		this.requestValidators = this.initializeValidators();

	}
	
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
			throw new FriendsterAPIException(e);
		}
		return requestValidators;
	}
	
	private Request initializeRequest(Map<String, String> requestParams) {
		return EvaluatingRequestBuilder.createRequest(requestParams);
	}
	
	public Response handleRequest() {
		this.performValidations();
		FlexibleHTTPClient f = new FlexibleHTTPClient(request);
		f.performHTTPRequest();
		return response;
	}

	private void performValidations() {
		for (RequestValidatorInterface validator : requestValidators) {
			validator.validateParams();
		}
	}
}