package com.friendster.api.client.throwable;

/**
 * Friendster API Exception
 * Friendster Inc.
 * 
 * @author Paulo Mendoza & Chiqui Mercene
 * @since Feb 15, 2012
 *
 */
public class FriendsterAPIException extends RuntimeException {
	private static final long serialVersionUID = -648052697036883255L;
	private Exception originalException;
	
	public FriendsterAPIException(Exception e) {
		this.originalException = e;
	}
	
	public FriendsterAPIException() {
		
	}

	public Exception getOriginalException() {
		return this.originalException;
	}
}
