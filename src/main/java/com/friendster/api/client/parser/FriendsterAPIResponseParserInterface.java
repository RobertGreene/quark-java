package com.friendster.api.client.parser;

import org.apache.http.HttpEntity;
import org.w3c.dom.Document;

public interface FriendsterAPIResponseParserInterface {

	public Document parseResponse(HttpEntity httpInput);
	
}
