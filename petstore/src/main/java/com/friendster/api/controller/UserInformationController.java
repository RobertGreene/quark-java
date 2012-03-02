package com.friendster.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.friendster.api.client.FriendsterAPIClient;
import com.friendster.api.form.UserRequest;
import com.friendster.api.v1.User;
import com.friendster.api.v1.UserResponse;

@Controller
public class UserInformationController {

	@RequestMapping(value = "/getUserInfoForm.html", method = RequestMethod.GET)
	public ModelAndView showGetUserForm() {
		return new ModelAndView("userIdForm", "command", new UserRequest());
	}

	@RequestMapping(value = "/getUserInformation.html", method = RequestMethod.POST)
	public ModelAndView getUserInformation(
			@ModelAttribute("userInfo") UserRequest user, BindingResult result) {
		FriendsterAPIClient client = new FriendsterAPIClient(
				"94e6592d-1cf0-dbbc-90ba-e391a36fc3d8",
				"62a37917567ef9f8d1765ab6c6dcea04",
				"b953de900014764557f3d37b43564958");

		UserRequest u = new UserRequest();

		if (user.getUserId() == null)
			user.setUserId("200000230");

		UserResponse userResponse = client.getUserInformation(Integer
				.parseInt(user.getUserId()));

		User u2 = userResponse.getUser().get(0);
		u.setUserId(u2.getUid());
		u.setName(u2.getFirstName() + " " + u2.getLastName());
		u.setLevel(u2.getLevel());

		return new ModelAndView("userinfo", "userInfo", u);
	}
}

//
// @RequestMapping(value = "/user", method = RequestMethod.GET)
// public ModelAndView getUserInformation(
// @RequestParam("user_id") String userId) {
// FriendsterAPIClient client = new FriendsterAPIClient(
// "94e6592d-1cf0-dbbc-90ba-e391a36fc3d8",
// "62a37917567ef9f8d1765ab6c6dcea04",
// "b953de900014764557f3d37b43564958");
//
// if (userId == null)
// userId = "200000230";
//
// UserInfo u = new UserInfo();
// UserResponse userResponse = client.getUserInformation(Integer
// .parseInt(userId));
// User user = userResponse.getUser().get(0);
// u.setUserId(user.getUid());
// u.setName(user.getFirstName() + " " + user.getLastName());
// u.setLevel(user.getLevel());
//
// return new ModelAndView("userinfo", "userInfo", u);
// }

// }