package com.friendster.client.example;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.friendster.api.beans.ApplicationFriendsResponse;
import com.friendster.api.beans.FriendsResponse;
import com.friendster.api.beans.GameScoreResponse;
import com.friendster.api.beans.MessageResponse;
import com.friendster.api.beans.NotificationsResponse;
import com.friendster.api.beans.ShoutoutResponse;
import com.friendster.api.beans.UserResponse;
import com.friendster.api.beans.topscores.HighScores;
import com.friendster.api.beans.topscores.Score;
import com.friendster.api.client.FriendsterAPIClient;
import com.friendster.api.client.special.MessageRequest;
import com.friendster.api.client.special.NotificationRequest;
import com.friendster.api.client.special.PaymentRequest;
import com.friendster.api.client.throwable.FriendsterAPIServiceException;

public class SampleClientImplementation {

	public static void main(String[] args) throws FileNotFoundException {
		String sessionKey = "ff4dc5e6-8c7b-7beb-b7a1-6f20e303d443";
		// ff4dc5e6-8c7b-7beb-b7a1-6f20e303d443
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
	@SuppressWarnings({ "rawtypes" })
	public static List getRequestList(FriendsterAPIClient client) {
		List requestList = new ArrayList();
		try {

			// // DONE
			// requestList.add(client.postShoutout("Hello World!"));
			// requestList.add(client.postNotification(new NotificationRequest(
			// "Hello", "Hello", "Hello", "Hello"), 200000233, 200000230));
			// requestList.add(client.postMessage(200000233, new MessageRequest(
			// 20, 20)));
			// requestList.add(client.postScore(200000233, 1000000));
			// requestList.add(client.getFriends(200000230));
			// requestList.add(client.getAppFriends());
			// requestList.add(client.getShoutout(200000230));
			// requestList.add(client.getWalletBalance());
			// requestList.add(client.getNewMessages());
			// requestList.add(client.getMessage(1));
			// requestList.add(client.getMessages());
			requestList.add(client.getUserInformation(200000230, 200000233));
			//
			// requestList.add(client.getTopScores());
			// requestList.add(client.getPaymentRequest(new
			// PaymentRequest("Test",
			// "Test", 1, "")));

			// IN PROGRESS
			// requestList.add(client.commitPaymentRequest("d1013626ad56f30657c8cc687e1485"));

			// requestList.add(client.getCallBackUrl(client.getPaymentRequest(new
			// PaymentRequest("Test", "Test",
			// 1, "")), "http://www.friendster.com"));

			// IGNORED

		} catch (FriendsterAPIServiceException e) {
			System.out.println("Error Code : " + e.getErrorCode());
			System.out.println("Error Msg  : " + e.getErrorMessage());
		}
		return requestList;
	}

	public static void displayResponse(Object o) {
		if (o instanceof UserResponse) {
			System.out.println("Successful: USER");
			UserResponse response = (UserResponse) o;

			System.out.println("Last name: "
					+ response.getUser().get(0).getFirstName().trim());
		}

		else if (o instanceof ShoutoutResponse) {
			System.out.println("Successful: SHOUTOUT_P");
			ShoutoutResponse response = (ShoutoutResponse) o;
			System.out.println("Status: " + response.getStatus());
		} else if (o instanceof FriendsResponse) {
			System.out.println("Successful: FRIENDS");
			List<String> f = ((FriendsResponse) o).getUid();

			for (String uf : f) {
				System.out.println("Friend : " + uf);
			}
		} else if (o instanceof GameScoreResponse) {
			System.out.println("SUCCESSFUL : SCORE");
			GameScoreResponse response = (GameScoreResponse) o;
			System.out.println("Status : " + response.getStatus());

		} else if (o instanceof com.friendster.api.beans.topscores.GameScoreResponse) {
			System.out.println("SUCCESSFUL : TOPSCORE");
			com.friendster.api.beans.topscores.GameScoreResponse response = (com.friendster.api.beans.topscores.GameScoreResponse) o;
			HighScores highScore = response.getHighScores();

			for (Score s : highScore.getScore()) {
				System.out.println("Avatar Id : " + s.getAvatar_id());
				System.out.println("Posted At : " + s.getPosted_at());
			}

		} else if (o instanceof com.friendster.api.beans.messages.MessageResponse) {
			System.out.println("SUCCESSFUL : GET MESSAGES");
			com.friendster.api.beans.messages.MessageResponse response = (com.friendster.api.beans.messages.MessageResponse) o;

			com.friendster.api.beans.messages.Messages messages = response
					.getMessages();
			if (messages != null) {
				com.friendster.api.beans.messages.Conversation conversation = messages
						.getConversation();
				System.out.println("Conversation --"
						+ conversation.getLatestMessageBody());
			}
		}
		// Get Message (with param)
		else if (o instanceof com.friendster.api.beans.message.MessageResponse) {
			System.out.println("SUCCESSFUL : GET MESSAGE (with param)");
			com.friendster.api.beans.message.MessageResponse response = (com.friendster.api.beans.message.MessageResponse) o;

			com.friendster.api.beans.message.Messages messages = response
					.getMessages();
			if (messages != null) {
				List<com.friendster.api.beans.message.Message> messageLst = messages
						.getMessage();

				for (com.friendster.api.beans.message.Message m : messageLst) {
					System.out.println("Message Body ---" + m.getMessageBody());

				}
			}
		} else if (o instanceof NotificationsResponse) {
			System.out.println("SUCCESSFUL : NOTIFICATIONS");
			NotificationsResponse response = (NotificationsResponse) o;
			for (String uid : (ArrayList<String>) response.getUids()) {
				System.out.println("UID : " + uid);
			}
		} else if (o instanceof ApplicationFriendsResponse) {
			System.out.println("SUCCESSFUL : APP_FRIENDS");
			ApplicationFriendsResponse response = (ApplicationFriendsResponse) o;
			for (String uf : response.getUid()) {
				System.out.println("UID: " + uf);
			}
		} else if (o instanceof MessageResponse) {
			System.out.println("SUCCESSFUL : MESSAGES");
			MessageResponse response = (MessageResponse) o;
			System.out.println("STATUS : " + response.getStatus());
		} else if (o instanceof com.friendster.api.beans.shoutout.ShoutoutResponse) {
			System.out.println("SUCCESSFUL : SHOUTOUT");
			com.friendster.api.beans.shoutout.ShoutoutResponse response = (com.friendster.api.beans.shoutout.ShoutoutResponse) o;
			System.out.println("Shoutout ID :----------------- "
					+ response.getShoutouts().getShoutout().getUid());
			System.out.println("Shoutout Content :----------------- "
					+ response.getShoutouts().getShoutout().getContent());
			System.out.println("Shoutout Updated :----------------- "
					+ response.getShoutouts().getShoutout().getUpdated());
		} else if (o instanceof com.friendster.api.beans.WalletResponse) {
			System.out.println("SUCCESSFUL : WALLET BALANCE");
			com.friendster.api.beans.WalletResponse response = (com.friendster.api.beans.WalletResponse) o;
			System.out.println("Wallet Coins: " + response.getCoins());
		} else if (o instanceof com.friendster.api.beans.wallet.payment.WalletResponse) {
			System.out.println("SUCCESSFUL : PAYMENT REQUEST");
			com.friendster.api.beans.wallet.payment.WalletResponse response = (com.friendster.api.beans.wallet.payment.WalletResponse) o;
			System.out.println("Redirect URL: " + response.getRedirectUrl());
			System.out.println("request Token : " + response.getRequestToken());
		} else if (o instanceof com.friendster.api.beans.wallet.commit.WalletResponse) {
			System.out.println("SUCCESSFUL : WALLET COMMIT");
			com.friendster.api.beans.wallet.commit.WalletResponse response = (com.friendster.api.beans.wallet.commit.WalletResponse) o;
			System.out.println("Amount: " + response.getAmt());
			System.out.println("Transaction ID : "
					+ response.getTransactionId());
			System.out.println("Timestamp : " + response.getTimestamp());
		} else if (o instanceof com.friendster.api.beans.NewmessagesResponse) {
			System.out.println("SUCCESSFUL: NEW MESSAGES");
			com.friendster.api.beans.NewmessagesResponse response = (com.friendster.api.beans.NewmessagesResponse) o;
			System.out.println("New Messages : " + response.getNewMsg());
			System.out.println("Last Updates : " + response.getLastUpdate());
		} else if (o instanceof URI) {
			System.out.println("SUCCESSFUL : WALLET_CALLBACK");
			URI response = (URI) o;
			try {
				System.out.println("Callback URL: "
						+ response.toURL().toString());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
	}
}