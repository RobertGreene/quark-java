package com.friendster.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.friendster.api.beans.SessionDetails;
import com.friendster.api.client.FriendsterAPIClient;

@Controller
public class HomeController {
	@Autowired
	private FriendsterAPIClient client;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView renderHomePage(
			@RequestParam("session_key") String sessionKey) {
		client.setSessionKey(sessionKey);
		SessionDetails sessionDetails = new SessionDetails();
		sessionDetails.setSessionKey(sessionKey);
		return new ModelAndView("home", "sessionDetails", sessionDetails);
	}
}