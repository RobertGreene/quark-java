package com.friendster.api.client.special;

import java.util.HashMap;
import java.util.Map;

public class MessageRequest {
	private Map<String, String> requestMap;
	
	public MessageRequest(Integer mid, Integer cid) {
		this.requestMap = new HashMap<String, String>();
		this.requestMap.put("mid", mid.toString());
		this.requestMap.put("cid", mid.toString());
	}

	public Map<String, String> getRequestMap() {
		return this.requestMap;
	}
}