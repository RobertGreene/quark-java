import com.friendster.api.client.FriendsterAPIClient;
import com.friendster.api.client.response.ResponseFormat;

public class Main {
	// public static void main(String[] args) {
	// Map<String, String> map = new HashMap<String, String>();
	// AppDetails app = new AppDetails();
	// app.setApiKey("6d014cc55fec6f7fc106bdbda12e7ec0");
	// app.setApiSecret("74dbc7249074d3f54690461278c4939f");
	// app.setSessionKey("8bf7b1b9-fe9b-7b4d-15b1-945150964f7e");
	//
	// List<Integer> uids = new ArrayList<Integer>();
	// uids.add(200000230);
	// // uids.add(200000233);
	// map.put("content", "Hello World!");
	//
	// RequestContext ctx = new RequestContext(RequestTypesEnum.SHOUTOUT_P,
	// app, map);
	// Response r = ctx.handleRequest();
	// System.out.println(r.getXmlResponse());
	// }

	public static void main(String[] args) {
		FriendsterAPIClient client = new FriendsterAPIClient(
				"311f184e-e844-a52b-edd4-3aa32a8ea060");
//		 System.out.println(client.getUserInformation(ResponseFormat.XML,
//		 200000230, 200000233));
		// System.out.println(client.getAppFriends(ResponseFormat.XML));
//		 System.out.println(client.getMessages(ResponseFormat.XML));
		// System.out.println(client.getFriends(ResponseFormat.XML, 200000230,
		// 200000233));
		// System.out.println(client.getShoutout(ResponseFormat.XML,
		// 200000230));
//		System.out.println(client.getTopScores(ResponseFormat.XML));
		
		System.out.println(client.getMessage(ResponseFormat.XML, 120));
	}

}

// <iframe class="" height="2485" id="game_iframe" scrolling="no"
// src="http://bso.ochospace.com/start.php?api_domain=www.friendster.com&amp;api_key=6d014cc55fec6f7fc106bdbda12e7ec0&amp;expires=1329802994&amp;instance_id=1&amp;lang=en&amp;nonce=1329716594.393304&amp;sandbox=false&amp;session_key=8bf7b1b9-fe9b-7b4d-15b1-945150964f7e&amp;src=canvas&amp;user_id=200636452&amp;signed_keys=api_domain,api_key,expires,instance_id,lang,nonce,sandbox,session_key,src,user_id,signed_keys&amp;sig=af70d7143864011f91dcc1c29e3c8857"
// width="760"></iframe>
//<iframe class height=​"2485" id=​"game_iframe" scrolling=​"no" src=​"http:​/​/​bso.ochospace.com/​start.php?api_domain=www.friendster.com&api_key=6d014cc55fec6f7fc106bdbda12e7ec0&expires=1329901336&instance_id=1&lang=en&nonce=1329814936.29577&sandbox=false&session_key=311f184e-e844-a52b-edd4-3aa32a8ea060&src=canvas&user_id=200636452&signed_keys=api_domain,api_key,expires,instance_id,lang,nonce,sandbox,session_key,src,user_id,signed_keys&sig=ed4e76913da5bb5c4e8fc6bf6f374931" width=​"760">​