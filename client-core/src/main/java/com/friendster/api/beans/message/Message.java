package com.friendster.api.beans.message;

import org.simpleframework.xml.Element;

public class Message {

	@Element(name = "mid")
	private String mid;
	
	@Element(name = "message_body")
	private String messageBody;
	
	@Element(name = "message_sender_id")
	private String messageSenderId;
	
	@Element(name = "message_sender_name")
	private String messageSenderName;
	
	@Element(name = "message_recipient_id")
	private String messageRecipientId;
	
	@Element(name = "message_recipient_name")
	private String messageRecipientName;
	
	public String getMid() {
		return mid;
	}
	public String getMessageBody() {
		return messageBody;
	}
	public String getMessageSenderId() {
		return messageSenderId;
	}
	public String getMessageSenderName() {
		return messageSenderName;
	}
	public String getMessageRecipientId() {
		return messageRecipientId;
	}
	public String getMessageRecipientName() {
		return messageRecipientName;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	public void setMessageSenderId(String messageSenderId) {
		this.messageSenderId = messageSenderId;
	}
	public void setMessageSenderName(String messageSenderName) {
		this.messageSenderName = messageSenderName;
	}
	public void setMessageRecipientId(String messageRecipientId) {
		this.messageRecipientId = messageRecipientId;
	}
	public void setMessageRecipientName(String messageRecipientName) {
		this.messageRecipientName = messageRecipientName;
	}
	
	
}
