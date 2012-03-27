package com.friendster.api.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.friendster.api.client.FriendsterAPIClient;
import com.friendster.api.client.throwable.FriendsterAPIServiceException;
import com.friendster.api.v1.WalletResponse;

@Controller
public class WalletPaymentCommitController {
	@Autowired
	private FriendsterAPIClient client;

	private static Logger logger = Logger
			.getLogger(WalletPaymentCommitController.class);

	@RequestMapping(value = "/wallet/commit", method = RequestMethod.GET)
	public ModelAndView commitPayment(
			@RequestParam("session_key") String sessionKey,
			@RequestParam("request_token") String requestToken,
			@RequestParam("amt") String requestAmount) {

		logger.debug("Wallet Commit");

		WalletResponse response = null;
		WalletResponse balance = null;
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