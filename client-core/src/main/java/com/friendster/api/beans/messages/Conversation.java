package com.friendster.api.beans.messages;

import org.simpleframework.xml.Element;


public class Conversation {
	
	@Element(name = "cid")
	private String cid;
		
	@Element(name = "latestMessageBody")
	private String latestMessageBody;
	
	@Element(name = "senderId")
	private String senderId;
	
	@Element(name = "senderName")
	private String senderName;
	
	@Element(name = "recipientId")
	private String recipientId;
	
	@Element(name = "recipientName")
	private String recipientName;

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
}
