package com.friendster.api.beans.messages;

import java.util.Date;

import org.simpleframework.xml.Element;


public class Conversation {
	
	@Element(name = "cid", required = false)
	private String cid;
		
	@Element(name = "latest_message_body", required = false)
	private String latestMessageBody;
	
	@Element(name = "sender_id", required = false)
	private String senderId;
	
	@Element(name = "sender_name", required = false)
	private String senderName;
	
	@Element(name = "recipient_id", required = false)
	private String recipientId;
	
	@Element(name = "recipient_name", required = false)
	private String recipientName;
	
	@Element(name = "time", required = false)
	private String time;

	public String getCid() {
		return cid;
	}

	public String getLatestMessageBody() {
		return latestMessageBody;
	}

	public String getSenderId() {
		return senderId;
	}

	public String getSenderName() {
		return senderName;
	}

	public String getRecipientId() {
		return recipientId;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public void setLatestMessageBody(String latestMessageBody) {
		this.latestMessageBody = latestMessageBody;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public Date getTime() {
		String time = this.time;
		Date returnDate = new Date();
		returnDate.setTime(Long.parseLong(time));
		return returnDate;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
