package com.friendster.api.client.parser;

import org.apache.http.HttpEntity;

import com.friendster.api.client.enums.RequestTypesEnum;

public interface FriendsterAPIResponseParserInterface {

	public Object parseResponse(RequestTypesEnum requestType, HttpEntity httpInput);
	
}