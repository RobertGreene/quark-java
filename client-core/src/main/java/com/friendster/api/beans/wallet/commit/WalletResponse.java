package com.friendster.api.beans.wallet.commit;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(strict = false)
@Namespace(reference = "http://api.friendster.com/v1/")
public class WalletResponse {
	
	@Element(name = "amt", required = false)
	private String amt;
	
	@Element(name = "transaction_id", required = false)
	private String transactionId;
	
	@Element(name = "timestamp", required = false)
	private String timestamp;
	
	public String getAmt() {
		return amt;
	}

	public String getTimestamp() {
		return timestamp;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
}
