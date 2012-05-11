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
import com.friendster.api.beans.WalletResponse;
import com.friendster.api.client.FriendsterAPIClient;
import com.friendster.api.client.throwable.FriendsterAPIServiceException;

@Controller
public class WalletBalanceController {
	@Autowired
	private FriendsterAPIClient client;
	
	@RequestMapping(value = "/wallet", method = RequestMethod.POST)
    public ModelAndView getWalletBalance(@RequestParam("session_key") String sessionKey, @RequestParam("user_id") String userId) {
		SessionDetails sessionDetails = new SessionDetails();
		sessionDetails.setSessionKey(sessionKey);
		client.setSessionKey(sessionKey);
		
		String walletBalance = null;
		try {
			WalletResponse walletResponse = client.getWalletBalance();
			walletBalance = walletResponse.getCoins();
		} catch (FriendsterAPIServiceException e) {
			
		}
        
        Map<String, Object> modelMap = new ModelMap();
        modelMap.put("userId", userId);
        modelMap.put("walletBalance", walletBalance);
        modelMap.put("sessionDetails", sessionDetails);
        
        return new ModelAndView("walletBalance", modelMap);
    }
}
