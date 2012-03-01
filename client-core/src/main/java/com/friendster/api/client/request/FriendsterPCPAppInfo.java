package com.friendster.api.client.request;

public class FriendsterPCPAppInfo {
	private String apiKey;
	private String apiSecret;
	private String nOnce;
	private String signature;
	private String sessionKey;
	
	public String getApiKey() {
		return apiKey;
	}
	public String getApiSecret() {
		return apiSecret;
	}
	public String getnOnce() {
		return nOnce;
	}
	public String getSignature() {
		return signature;
	}
	public String getSessionKey() {
		return sessionKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}
	public void setnOnce(String nOnce) {
		this.nOnce = nOnce;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

}
