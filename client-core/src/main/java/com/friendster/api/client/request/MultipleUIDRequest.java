package com.friendster.api.client.request;

import java.util.HashMap;
import java.util.List;
import com.friendster.api.client.enums.RequestType;

public class MultipleUIDRequest extends Request {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7539689645174946732L;
	private String uids;

	public MultipleUIDRequest(RequestType requestType,
			FriendsterPCPAppInfo appDetails, List<Integer> args) {
		super(requestType, appDetails);
		this.otherParams = new HashMap<String, String>();
		this.uids = this.marshalUIDs(args);
		this.otherParams.put("uids", this.uids);
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