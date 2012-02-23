import java.io.FileNotFoundException;

import com.friendster.api.client.FriendsterAPIClient;
import com.friendster.api.client.special.AvatarScore;
import com.friendster.api.client.special.AvatarScoreResponse;
import com.friendster.api.client.special.NotificationRequest;
import com.friendster.api.v1.ShoutoutResponse;
import com.friendster.api.v1.User;
import com.friendster.api.v1.UserResponse;
import com.friendster.api.v1.friends.Friends;
import com.friendster.api.v1.friends.FriendsResponse;
import com.friendster.api.v1.message.Message;
import com.friendster.api.v1.message.MessageResponse;
import com.friendster.api.v1.message.Messages;
import com.friendster.api.v1.notification.NotificationsResponse;
import com.friendster.api.v1.score.GameScoreResponse;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		FriendsterAPIClient client = new FriendsterAPIClient(
				"4bcc082d-6709-bd9d-7830-a4a6fee15797");

//		Object o = client.postShoutout("Hello Paulo!");

//		 Object o = client.getUserInformation(200000230, 200000233);
//		 Object o = client.getFriends(200000230);

//		Object o = client.getTopScores();
//		Object o = client.postScore(200000233, 1000000);
//		Object o = client.getMessage(1);
//		Object o = client.postMessage(200000233, new MessageRequest(20, 20));
		Object o = client.postNotification(new NotificationRequest("Hello", "Hello", "Hello", "Hello"), 200000233, 200000230);

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
				System.out.println("Score : " + as.getScore() + 
						" Avatar ID : "	+ as.getAvatarId() + 
						" Posted At : "	+ as.getPostedAt());
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
			for (String uid : response.getUid()) {
				System.out.println("UID : " + uid);	
			}
		}
	}
}
