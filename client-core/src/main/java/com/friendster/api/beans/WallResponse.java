package com.friendster.api.beans.wall;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(strict = false)
@Namespace(reference = "http://api.friendster.com/v1/")
public class WallResponse {
  
//    @XmlElement(name = "id")
//    @XmlElement(name = "created_at")
	
	@Element(name = "id", required = false)
	private String Id;
	
	@Element(name = "created_at", required = false)
	private String CreatedAt;

	public String getCreatedAt() {
		return CreatedAt;
	}

	public String getId() {
		return Id;
	}

	public void setCreatedAt(String CreatedAt) {
		this.CreatedAt = CreatedAt;
	}

	public void setRedirectUrl(String Id) {
		this.Id = Id;
	}
	
}
