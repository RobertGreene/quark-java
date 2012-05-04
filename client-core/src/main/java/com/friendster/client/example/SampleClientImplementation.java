package com.friendster.client.example;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.friendster.api.beans.NotificationsResponse;
import com.friendster.api.beans.ShoutoutResponse;
import com.friendster.api.client.FriendsterAPIClient;
import com.friendster.api.client.special.AvatarScore;
import com.friendster.api.client.special.AvatarScoreResponse;
import com.friendster.api.client.special.NotificationRequest;
import com.friendster.api.client.throwable.FriendsterAPIServiceException;
import com.friendster.api.v1.NewmessagesResponse;
import com.friendster.api.v1.User;
import com.friendster.api.v1.UserResponse;
import com.friendster.api.v1.WalletResponse;
import com.friendster.api.v1.friends.Friends;
import com.friendster.api.v1.friends.FriendsResponse;
import com.friendster.api.v1.message.Message;
import com.friendster.api.v1.message.MessageResponse;
import com.friendster.api.v1.message.Messages;
import com.friendster.api.v1.score.GameScoreResponse;
import com.friendster.api.v1.shoutout_list.Shoutout;

public class SampleClientImplementation {

	public static void main(String[] args) throws FileNotFoundException {

		String sessionKey = "4e0f6230-30ef-a469-5bf8-4857b1ca63f6";
		FriendsterAPIClient client = new FriendsterAPIClient(sessionKey,
				"src/main/resources/FriendsterAPIConfig.xml");
		for (Object responseObject : getRequestList(client)) {
			displayResponse(responseObject);
		}
	}

	/**
	 * @param client
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List getRequestList(FriendsterAPIClient client) {
		List requestList = new ArrayList();
		try {
			// DONE
			// requestList.add(client.postShoutout("Hello World!"));

			// IN PROGRESS
			requestList.add(client.postNotification(new NotificationRequest(
					"Hello", "Hello", "Hello", "Hello"), 200000233, 200000230));

			// IGNORED

			// requestList.add(client.getMessages());
			// requestList.add(client.getUserInformation(200000230, 200000233));
			// requestList.add(client.getFriends(200000230));
			// requestList.add(client.getTopScores());
			// requestList.add(client.postScore(200000233, 1000000));
			// requestList.add(client.getMessage(1));
			// requestList.add(client.postMessage(200000233, new MessageRequest(
			// 20, 20)));

			// requestList.add(client.getAppFriends());
			// requestList.add(client.getShoutout(200000230));
			// requestList.add(client.getWalletBalance());
			// requestList.add(client.getPaymentRequest(new
			// PaymentRequest("Test",
			// "Test", 1, "")));
			// requestList.add(client
			// .commitPaymentRequest("ea9acc5cc6d607dab18dd92cf9d7c4"));
			// requestList.add(client.getNewMessages());
			// System.out.println(requestList.add(client.getNewMessages()));

			// requestList.add(client.getCallBackUrl(
			// client.getPaymentRequest(new PaymentRequest("Test", "Test",
			// 1, "")), "http://www.friendster.com"));

		} catch (FriendsterAPIServiceException e) {
			System.out.println("Error Code : " + e.getErrorCode());
			System.out.println("Error Msg  : " + e.getErrorMessage());
		}
		return requestList;
	}

	@SuppressWarnings("unchecked")
	public static void displayResponse(Object o) {
		if (o instanceof UserResponse) {
			System.out.println("Successful");
			UserResponse response = (UserResponse) o;
			for (User u : response.getUser()) {
				System.out.println("User: " + u.getFirstName().trim());
			}
		} else if (o instanceof ShoutoutResponse) {
			System.out.println("Successful: SHOUTOUT_P");
			ShoutoutResponse response = (ShoutoutResponse) o;
			System.out.println("Status: " + response.getStatus());
		} else if (o instanceof FriendsResponse) {
			System.out.println("Successful: FRIENDS");
			Friends f = ((FriendsResponse) o).getFriends();
			for (String uid : f.getUid()) {
				System.out.println("Friend : " + uid);
			}
		} else if (o instanceof AvatarScoreResponse) {
			AvatarScoreResponse scoreResponse = (AvatarScoreResponse) o;
			for (AvatarScore as : scoreResponse.getScores())
				System.out
						.println("Score : " + as.getScore() + " Avatar ID : "
								+ as.getAvatarId() + " Posted At : "
								+ as.getPostedAt());
		} else if (o instanceof GameScoreResponse) {
			System.out.println("SUCCESSFUL : SCORE");
			GameScoreResponse response = (GameScoreResponse) o;
			System.out.println("Status : " + response.getStatus());
		} else if (o instanceof MessageResponse) {
			System.out.println("SUCCESSFUL : MESSAGE");
			MessageResponse response = (MessageResponse) o;
			Messages messages = response.getMessages();
			if (messages != null) {
				for (Message m : messages.getMessage()) {
					System.out.println("Message : " + m.getMessageBody());
				}
			} else {
				System.out.println("Status : " + response.getStatus());
				System.out.println("No Messages");
			}
		} else if (o instanceof NotificationsResponse) {
			System.out.println("SUCCESSFUL : NOTIFICATIONS");
			NotificationsResponse response = (NotificationsResponse) o;
			for (String uid : (ArrayList<String>) response.getUids()) {
				System.out.println("UID : " + uid);
			}
		} else if (o instanceof com.friendster.api.v1.messages_get.MessageResponse) {
			System.out.println("SUCCESSFUL : MESSAGES");
			@SuppressWarnings("unused")
			com.friendster.api.v1.messages_get.MessageResponse response = (com.friendster.api.v1.messages_get.MessageResponse) o;
		} else if (o instanceof com.friendster.api.v1.shoutout_list.ShoutoutResponse) {
			System.out.println("SUCCESSFUL : SHOUTOUT");
			com.friendster.api.v1.shoutout_list.ShoutoutResponse response = (com.friendster.api.v1.shoutout_list.ShoutoutResponse) o;
			for (Shoutout shoutout : response.getShoutouts().getShoutout()) {
				System.out
						.println("Shoutout : " + shoutout.getUid() + " : "
								+ shoutout.getContent() + " : "
								+ shoutout.getUpdated());
			}
		} else if (o instanceof WalletResponse) {
			System.out.println("SUCCESSFUL : WALLET");
			WalletResponse response = (WalletResponse) o;
			System.out.println("Wallet Coins: " + response.getCoins());
			System.out.println("Amount : " + response.getAmt());
			System.out.println("Redirect URL : " + response.getRedirectUrl());
		} else if (o instanceof NewmessagesResponse) {
			System.out.println("SUCCESSFUL: NEW MESSAGES");
			NewmessagesResponse response = (NewmessagesResponse) o;
			System.out.println("New Messages : " + response.getNew());
			System.out.println("Last Updates : " + response.getLastUpdate());
		} else if (o instanceof URI) {
			System.out.println("SUCCESSFUL : WALLET_CALLBACK");
			URI response = (URI) o;
			try {
				System.out.println("Callback URL: "
						+ response.toURL().toString());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}