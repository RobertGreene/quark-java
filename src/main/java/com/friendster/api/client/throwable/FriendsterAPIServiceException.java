package com.friendster.api.client.throwable;

public class FriendsterAPIServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4830604796785304455L;

	private String errorCode;
	private String errorMessage;
	
	public FriendsterAPIServiceException(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public String getErrorCode() {
		return this.errorCode;
	}
	
	public String getErrorMessage() {
		return this.errorMessage;
	}
}
