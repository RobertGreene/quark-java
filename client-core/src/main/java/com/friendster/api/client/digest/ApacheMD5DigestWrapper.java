package com.friendster.api.client.digest;

import org.apache.commons.codec.digest.DigestUtils;

public class ApacheMD5DigestWrapper implements FriendsterAPIDigestInterface {

	
	public String getHexDigest(String digestInput) {
		return DigestUtils.md5Hex(digestInput);
	}

}