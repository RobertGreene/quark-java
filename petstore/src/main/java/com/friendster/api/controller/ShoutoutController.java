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
import com.friendster.api.beans.ShoutoutResponse;
import com.friendster.api.client.FriendsterAPIClient;
import com.friendster.api.client.throwable.FriendsterAPIServiceException;

@Controller
public class ShoutoutController {
	@Autowired
	private FriendsterAPIClient client;

	@RequestMapping(value = "/shoutout", method = RequestMethod.POST)
	public ModelAndView getUserInformation(
			@RequestParam("session_key") String sessionKey,
			@RequestParam("shoutout_text") String shoutoutText) {
		SessionDetails sessionDetails = new SessionDetails();
		sessionDetails.setSessionKey(sessionKey);
		client.setSessionKey(sessionKey);
		
		String shoutoutStatus = null;
		
		try {
			ShoutoutResponse shoutoutResponse = client.postShoutout(shoutoutText);
			shoutoutStatus = shoutoutResponse.getStatus();
		} catch (FriendsterAPIServiceException e) {
			shoutoutStatus = e.getErrorCode() + " "
					+ e.getErrorMessage();
		}
		
		Map<String, Object> modelMap = new ModelMap();
		modelMap.put("sessionDetails", sessionDetails);
		modelMap.put("shoutoutStatus", shoutoutStatus);
		modelMap.put("shoutout", shoutoutText);
		
		return new ModelAndView("shoutoutStatus", modelMap);
	}
}