package com.friendster.api.client.special;

import java.util.HashMap;
import java.util.Map;

public class PaymentRequest {
	private Map<String, String> paramsMap;
	
	public PaymentRequest(String name, String description, Integer amt, String params) {
		this.paramsMap = new HashMap<String, String>();
		this.paramsMap.put("name"	, name);
		this.paramsMap.put("description", description);
		this.paramsMap.put("amt", String.valueOf(amt));
		this.paramsMap.put("params", params);
	}
	
	public Map<String, String> getPaymentParams() {
		return this.paramsMap;
	}
}