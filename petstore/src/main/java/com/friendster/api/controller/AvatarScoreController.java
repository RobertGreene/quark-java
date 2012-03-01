package com.friendster.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.friendster.api.client.FriendsterAPIClient;
import com.friendster.api.client.special.AvatarScore;
import com.friendster.api.client.special.AvatarScoreResponse;
import com.friendster.api.form.UserInfo;

@Controller
public class AvatarScoreController {

	@RequestMapping(value = "/score", method = RequestMethod.GET)
	public ModelAndView getUserInformation() {
		FriendsterAPIClient client = new FriendsterAPIClient(
				"94e6592d-1cf0-dbbc-90ba-e391a36fc3d8",
				"62a37917567ef9f8d1765ab6c6dcea04",
				"b953de900014764557f3d37b43564958");
		AvatarScoreResponse a = client.getTopScores();
		UserInfo u = new UserInfo();
		for (AvatarScore as : a.getScores()) {
			u.setScore(as);
		}
		return new ModelAndView("topscores", "userInfo", u);
	}

}