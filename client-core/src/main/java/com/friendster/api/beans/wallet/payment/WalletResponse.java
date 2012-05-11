package com.friendster.api.beans.wallet.payment;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(strict = false)
@Namespace(reference = "http://api.friendster.com/v1/")
public class WalletResponse {
	
//    @XmlElement(name = "request_token")
//    protected String requestToken;
//    @XmlElement(name = "redirect_url")
	
	@Element(name = "request_token", required = false)
	private String requestToken;
	
	@Element(name = "redirect_url", required = false)
	private String redirectUrl;

	public String getRequestToken() {
		return requestToken;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRequestToken(String requestToken) {
		this.requestToken = requestToken;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	
}
