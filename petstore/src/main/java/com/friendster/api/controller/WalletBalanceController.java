package com.friendster.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.friendster.api.client.FriendsterAPIClient;
import com.friendster.api.form.UserRequest;


@Controller
public class WalletBalanceController {
    @RequestMapping("/wallet")
    public ModelAndView walletBalance() {
 
		FriendsterAPIClient client = new FriendsterAPIClient(
				"94e6592d-1cf0-dbbc-90ba-e391a36fc3d8",
				"62a37917567ef9f8d1765ab6c6dcea04",
				"b953de900014764557f3d37b43564958");
		UserRequest u = new UserRequest();
        
        u.setUserId(client.getUserInformation(200000230).getUser().get(0).getUid());
        u.setCoins(client.getWalletBalance().getCoins());
        
        return new ModelAndView("wallet", "userInfo", u);
    }
}
