package com.friendster.api.client.request;

import java.util.List;

import org.apache.log4j.Logger;

import com.friendster.api.client.enums.RequestTypesEnum;

public class MultipleUIDRequest extends Request {
	private static Logger logger = Logger.getLogger(MultipleUIDRequest.class);
	private String uids;

	public MultipleUIDRequest(RequestTypesEnum requestType,
			AppDetails appDetails, List<Integer> args) {
		super(requestType, appDetails);
		this.uids = this.marshalUIDs(args);
		logger.debug("Request UIDs : " + this.uids);
	}

	private String marshalUIDs(List<Integer> args) {
		StringBuffer uids = new StringBuffer();
		for (Integer i : args) {
			uids.append(String.valueOf(i) + ", ");
		}
		return uids.substring(0, uids.lastIndexOf(",")).toString();
	}
	
	public String getUIDs() {
		return this.uids;
	}
}