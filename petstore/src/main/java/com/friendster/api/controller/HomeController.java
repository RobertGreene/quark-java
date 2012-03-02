package com.friendster.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.friendster.api.form.AppDetails;

@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView renderHomePage(@RequestParam("api_key") String apiKey,
			@RequestParam("session_key") String sessionKey,
			@RequestParam("expires") String expiryDate) {
		AppDetails appDetails = new AppDetails();
		appDetails.setApiKey(apiKey);
		appDetails.setApiSecret("b953de900014764557f3d37b43564958");
		appDetails.setSessionKey(sessionKey);
		appDetails.setExpiryDate(expiryDate);
		
		return new ModelAndView("home", "appDetails", appDetails);
	}
}