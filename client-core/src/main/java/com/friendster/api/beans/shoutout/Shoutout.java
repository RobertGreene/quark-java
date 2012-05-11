package com.friendster.api.beans.shoutout;

import org.simpleframework.xml.Element;

public class Shoutout {
	
	@Element(name = "uid")
    private String uid;
    
	@Element(name = "content")
    private String content;
    
	@Element(name = "updated")
    private String updated;
    
    
	public String getUid() {
		return uid;
	}
	public String getContent() {
		return content;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
    
    
    
}
