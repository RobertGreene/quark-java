package com.friendster.api.client.response;

import org.w3c.dom.Document;

public class Response {
	private final Document xmlResponse;

	public Response(Document xmlResponse) {
		this.xmlResponse = xmlResponse;
	}

	public Document getXmlResponse() {
		return xmlResponse;
	}

}