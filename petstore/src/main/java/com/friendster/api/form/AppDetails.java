package com.friendster.api.form;

import java.util.Calendar;
import java.util.Date;

public class AppDetails {
	private String apiKey;
	private String apiSecret;
	private String sessionKey;
	private Date expiryDate;

	public String getApiKey() {
		return apiKey;
	}

	public String getApiSecret() {
		return apiSecret;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public void setApiSecret(String apiSecret) {
		this.apiSecret = apiSecret;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public void setExpiryDate(String expiryDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(Long.parseLong(expiryDate));
		this.expiryDate = calendar.getTime();
	}
}
