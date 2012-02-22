import java.io.FileNotFoundException;

import com.friendster.api.client.FriendsterAPIClient;
import com.friendster.api.client.response.ResponseFormat;
import com.friendster.api.v1.UserResponseType;
import com.friendster.api.v1.UserType;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		FriendsterAPIClient client = new FriendsterAPIClient(
				"311f184e-e844-a52b-edd4-3aa32a8ea060");
		
//		Object obj = client.postShoutout("Hello World!");
		
		Object o = client.getUserInformation(ResponseFormat.XML, 200000230,
				200000233);

		if (o instanceof UserResponseType) {
			System.out.println("Successful");
			UserResponseType response = (UserResponseType) o;
			for (UserType u : response.getUser()) {
				System.out.println("User: " + u.getFirstName().trim());
			}
		}
	}
}