package com.friendster.api.client.special;

import java.util.HashMap;
import java.util.Map;

public class NotificationRequest {
	private Map<String, String> notificationParams = new HashMap<String, String>();

	public NotificationRequest(String content, String subject, String label,
			String type) {
		notificationParams = new HashMap<String, String>();
		notificationParams.put("content", content);
		notificationParams.put("subject", subject);
		notificationParams.put("label", label);
		notificationParams.put("type", type);
	}

	public Map<String, String> getNotificationParams() {
		return this.notificationParams;
	}
}