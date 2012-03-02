package com.friendster.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.friendster.api.client.FriendsterAPIClient;
import com.friendster.api.client.throwable.FriendsterAPIServiceException;
import com.friendster.api.form.AppDetails;
import com.friendster.api.form.UserRequest;
import com.friendster.api.v1.ShoutoutResponse;

@Controller
public class ShoutoutController {

	@RequestMapping(value = "/shoutout", method = RequestMethod.GET)
	public ModelAndView showShoutoutMsgForm(
			@RequestParam("api_key") String apiKey,
			@RequestParam("api_secret") String apiSecret,
			@RequestParam("session_key") String sessionKey) {

		AppDetails apd = new AppDetails();
		apd.setApiKey(apiKey);
		apd.setApiSecret(apiSecret);
		apd.setSessionKey(sessionKey);

		return new ModelAndView("postShoutoutForm", "appDetails", apd);
	}

	@RequestMapping(value = "/shoutout", method = RequestMethod.POST)
	public ModelAndView getUserInformation(
			@RequestParam("shoutout") String shoutout,
			@RequestParam("api_key") String apiKey,
			@RequestParam("api_secret") String apiSecret,
			@RequestParam("session_key") String sessionKey) {
		FriendsterAPIClient client = new FriendsterAPIClient(sessionKey, apiKey, apiSecret);

		UserRequest u = new UserRequest();
		try {
			ShoutoutResponse shoutoutResponse = client.postShoutout(shoutout);
			u.setShoutoutMsg(shoutoutResponse.getStatus());
		} catch (FriendsterAPIServiceException e) {
			u.setShoutoutMsg(e.getErrorCode() + " " + e.getErrorMessage());
		}


		return new ModelAndView("postShoutout", "userInfo", u);
	}
}