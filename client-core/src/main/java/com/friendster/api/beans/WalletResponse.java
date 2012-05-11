package com.friendster.api.beans;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(strict = false)
@Namespace(reference = "http://api.friendster.com/v1")
public class WalletResponse {

	@Element(name = "coins")
	private String coins;
	
	@Element(name = "timestamp")
	private String timestamp;

	public String getCoins() {
		return coins;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setCoins(String coins) {
		this.coins = coins;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
