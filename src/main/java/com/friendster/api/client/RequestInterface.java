package com.friendster.api.client;

import com.friendster.api.client.throwable.FriendsterAPIException;

/**
 * Base Request Interface for Friendster API Client Friendster Inc.
 * 
 * @author Paulo Mendoza & Chiqui Mercene
 * @since Feb 15, 2012
 * 
 */

public interface RequestInterface {

	public byte[] generateSignature() throws FriendsterAPIException;

}
