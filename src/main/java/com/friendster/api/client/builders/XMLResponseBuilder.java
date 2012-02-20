package com.friendster.api.client.builders;

import org.w3c.dom.Document;

import com.friendster.api.client.response.Response;

public class XMLResponseBuilder {

	public static Response buildResponse(Document parseResponse) {
		
		return new Response(parseResponse);
	}

}