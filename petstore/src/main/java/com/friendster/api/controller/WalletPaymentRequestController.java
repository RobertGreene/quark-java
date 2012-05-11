package com.friendster.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.friendster.api.beans.SessionDetails;
import com.friendster.api.beans.wallet.payment.WalletResponse;
import com.friendster.api.client.FriendsterAPIClient;
import com.friendster.api.client.special.PaymentRequest;
import com.friendster.api.client.throwable.FriendsterAPIServiceException;

@Controller
public class WalletPaymentRequestController {
	@Autowired
	private FriendsterAPIClient client;
		
	@RequestMapping(value = "/wallet/payment", method = RequestMethod.POST)
    public ModelAndView getWalletBalance(@RequestParam("session_key") String sessionKey, @RequestParam("amount") String amount) {
		SessionDetails sessionDetails = new SessionDetails();
		sessionDetails.setSessionKey(sessionKey);
		client.setSessionKey(sessionKey);
		String redirectURL = "http://smackaho.st:8080/petstore/wallet/commit";
		WalletResponse walletResponse = null;
		
		try {
			walletResponse = client.getPaymentRequest(new PaymentRequest("Quark Java (Test)", "Test Credits Purchase", Integer.parseInt(amount), ""));
			redirectURL = client.getCallBackUrl(walletResponse, redirectURL).toString();
		} catch (FriendsterAPIServiceException e) {
			e.printStackTrace();
		}
        
        return new ModelAndView("redirect:" + redirectURL);
        
    }
}