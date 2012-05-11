package com.friendster.api.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.friendster.api.beans.SessionDetails;
import com.friendster.api.beans.User;
import com.friendster.api.beans.UserResponse;
import com.friendster.api.client.FriendsterAPIClient;
import com.friendster.api.client.throwable.FriendsterAPIServiceException;

@Controller
public class UserInformationController {
	@Autowired
	private FriendsterAPIClient client;
	private static Logger logger = Logger.getLogger(UserInformationController.class);

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ModelAndView getUserInformation(
			@RequestParam("user_id") String userId,
			@RequestParam("session_key") String sessionKey) {
		SessionDetails sessionDetails = new SessionDetails();
		sessionDetails.setSessionKey(sessionKey);
		client.setSessionKey(sessionKey);

		try {
			client.getUserInformation(userId);
		} catch (FriendsterAPIServiceException e) {
		}
		UserResponse userResponse = client.getUserInformation(Integer
				.parseInt(userId));
		logger.debug("Size >>>>>>>>>>>>>>>>>>>>>>>>>>>>" + userResponse.getUser().size());
		User user = userResponse.getUser().get(0);
		

		Map<String, Object> modelMap = new ModelMap();
		modelMap.put("sessionDetails", sessionDetails);
		modelMap.put("userInfo", user);
		return new ModelAndView("userInfo", modelMap);
	}
}