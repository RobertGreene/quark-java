package com.friendster.api.client;

import com.friendster.api.client.enums.RequestTypesEnum;
import com.friendster.api.client.request.AppDetails;
import com.friendster.api.client.response.ResponseFormat;

/* Facade for Friendster API Client
 * Friendster Inc.
 * @author Paulo Mendoza
 * Feb 21, 2012
 * 
 * This is the facade class for the Friendster API Client, it is meant to be instantiated with the session
 * scope. Multiple requests can be invoked.
 * */
public class FriendsterAPIClient {
	private AppDetails appDetails;
	
	public FriendsterAPIClient(String sessionKey) {
		this.appDetails = this.createAppDetails(sessionKey);
	}

	public Object getUserInformation(ResponseFormat responseFormat, Object... uids) {
		RequestContext requestContext = new RequestContext(RequestTypesEnum.USER, this.appDetails, uids);
		return requestContext.handleRequest(responseFormat);
	}
	
	public Object getAppFriends(ResponseFormat responseFormat) {
		RequestContext requestContext = new RequestContext(RequestTypesEnum.APP_FRIENDS, this.appDetails);
		return requestContext.handleRequest(responseFormat);
	}
	
	
	private AppDetails createAppDetails(String sessionKey) {
		this.appDetails = new AppDetails();
		this.appDetails.setApiKey("6d014cc55fec6f7fc106bdbda12e7ec0");
		this.appDetails.setApiSecret("74dbc7249074d3f54690461278c4939f");
		this.appDetails.setSessionKey(sessionKey);
		
		return appDetails;
	}
}
