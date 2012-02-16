package com.friendster.api.client.builders;

import java.util.Map;

import com.friendster.api.client.enums.RequestTypesEnum;
import com.friendster.api.client.request.Request;

public class EvaluatingRequestBuilder {

	public static Request createRequest(Map<String, String> requestParams) {
		return new Request(RequestTypesEnum.USER, requestParams.remove("apiKey")
				.toString(), requestParams.remove("apiSecret").toString(),
				requestParams.remove("sessionKey").toString(), requestParams);
	}
}