package com.friendster.api.client.request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.friendster.api.client.enums.RequestTypesEnum;

public class MultipleUIDRequest extends Request {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7539689645174946732L;
	private static Logger logger = Logger.getLogger(MultipleUIDRequest.class);
	private String uids;
	
	@SuppressWarnings("unchecked")
	public MultipleUIDRequest(RequestTypesEnum requestType,
			AppDetails appDetails, Object... args) {
		super(requestType, appDetails);
		
		for (Object o : args) {
			if (o instanceof Map) {
				this.otherParams = new HashMap<String, String>((Map<String, String>) o);
			} else {
				this.otherParams = new HashMap<String, String>();					
			}
		}
		for (Object o : args) {
			if (o instanceof List) {
				this.uids = this.marshalUIDs((List<Integer>) o);
			} else {

			}
		}
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