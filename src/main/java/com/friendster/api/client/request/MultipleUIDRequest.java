package com.friendster.api.client.request;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.friendster.api.client.enums.RequestTypesEnum;

public class MultipleUIDRequest extends Request {
	private static Logger logger = Logger.getLogger(MultipleUIDRequest.class);
	private String uids;

	public MultipleUIDRequest(RequestTypesEnum requestType,
			AppDetails appDetails, List<Integer> args) {
		super(requestType, appDetails);
		this.otherParams = new HashMap<String, String>();
		this.uids = this.marshalUIDs(args);
		this.otherParams.put("uids", this.uids);
		logger.debug("Request UIDs : " + this.getUIDs());
	}

	private String marshalUIDs(List<Integer> args) {
		StringBuffer uids = new StringBuffer();
		for (Integer i : args) {
			uids.append(String.valueOf(i) + ",");
		}
		if (uids.lastIndexOf(",") != -1)
			return uids.substring(0, uids.lastIndexOf(",")).toString();
		else
			return uids.toString();
	}

	public String getUIDs() {
		return this.uids;
	}
}