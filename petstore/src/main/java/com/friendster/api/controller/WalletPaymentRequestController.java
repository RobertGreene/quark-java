package com.friendster.api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.friendster.api.beans.SessionDetails;
import com.friendster.api.client.FriendsterAPIClient;
import com.friendster.api.client.special.PaymentRequest;
import com.friendster.api.client.throwable.FriendsterAPIServiceException;
import com.friendster.api.v1.WalletResponse;

@Controller
public class WalletPaymentRequestController {
	@Autowired
	private FriendsterAPIClient client;
	
	@RequestMapping(value = "/wallet/payment", method = RequestMethod.POST)
    public ModelAndView getWalletBalance(@RequestParam("session_key") String sessionKey, @RequestParam("amount") String amount) {
		SessionDetails sessionDetails = new SessionDetails();
		sessionDetails.setSessionKey(sessionKey);
		client.setSessionKey(sessionKey);
		
		String callbackUrl = null;
		String requestToken = null;
		try {
			WalletResponse walletResponse = client.getPaymentRequest(new PaymentRequest("Hello", "Hello", Integer.parseInt(amount), ""));
			amount = walletResponse.getAmt();
			callbackUrl = walletResponse.getRedirectUrl();
			requestToken = walletResponse.getRequestToken();
		} catch (FriendsterAPIServiceException e) {
			
		}
        
        Map<String, Object> modelMap = new ModelMap();
        modelMap.put("amount", amount);
        modelMap.put("callback_url", callbackUrl);
        modelMap.put("request_token", requestToken);
        modelMap.put("sessionDetails", sessionDetails);
        
        return new ModelAndView("walletPayment", modelMap);
    }
}
