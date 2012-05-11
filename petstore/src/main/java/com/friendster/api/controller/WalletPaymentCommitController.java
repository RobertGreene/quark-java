package com.friendster.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.friendster.api.beans.wallet.commit.WalletResponse;
import com.friendster.api.client.FriendsterAPIClient;
import com.friendster.api.client.throwable.FriendsterAPIServiceException;

@Controller
public class WalletPaymentCommitController {
	@Autowired
	private FriendsterAPIClient client;


	@RequestMapping(value = "/wallet/commit", method = RequestMethod.GET)
	public ModelAndView commitPayment(
			@RequestParam("session_key") String sessionKey,
			@RequestParam("request_token") String requestToken,
			@RequestParam("amt") String requestAmount) {


		WalletResponse response = null;
		com.friendster.api.beans.WalletResponse balance = null;
		try {
			response = client.commitPaymentRequest(requestToken);
			balance = client.getWalletBalance();
		} catch (FriendsterAPIServiceException e) {
			e.printStackTrace();
		}

		ModelMap returnMap = new ModelMap();
		returnMap.put("requestAmount", requestAmount);
		returnMap.put("debitAmount", (response.getAmt() == null ? "0"
				: response.getAmt()));
		returnMap.put("sessionKey", sessionKey);
		returnMap.put("transId", response.getTransactionId());
		returnMap.put("transTime", response.getTimestamp());
		returnMap.put("balance", balance.getCoins());

		return new ModelAndView("walletCommit", returnMap);
	}
}