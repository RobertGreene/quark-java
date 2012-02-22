import java.io.FileNotFoundException;

import com.friendster.api.client.FriendsterAPIClient;
import com.friendster.api.v1.ShoutoutResponse;
import com.friendster.api.v1.User;
import com.friendster.api.v1.UserResponse;
import com.friendster.api.v1.friends.Friends;
import com.friendster.api.v1.friends.FriendsResponse;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		FriendsterAPIClient client = new FriendsterAPIClient(
				"4bcc082d-6709-bd9d-7830-a4a6fee15797");

//		Object o = client.postShoutout("Hello Paulo!");

//		Object o = client.getUserInformation(200000230, 200000233);
		Object o = client.getFriends(200000230, 200000233);

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
			FriendsResponse response = (FriendsResponse) o;
			Friends f = ((FriendsResponse) o).getFriends();
			for (String uid : f.getUid()) {
				System.out.println("Friend : " + uid);
			}
			
		}
	}
}