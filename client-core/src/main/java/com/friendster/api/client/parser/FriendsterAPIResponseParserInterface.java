package com.friendster.api.client.parser;

import org.apache.http.HttpEntity;
import com.friendster.api.client.enums.RequestType;

public interface FriendsterAPIResponseParserInterface {
	
	public Object parseResponse(RequestType requestType, HttpEntity httpInput);
	
}
