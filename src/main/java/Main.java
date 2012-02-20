import java.util.HashMap;
import java.util.Map;

import com.friendster.api.client.RequestContext;
import com.friendster.api.client.response.Response;

public class Main {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("uids", "200000230");
		map.put("apiKey", "6d014cc55fec6f7fc106bdbda12e7ec0");
		                 //6d014cc55fec6f7fc106bdbda12e7ec0
		map.put("apiSecret", "74dbc7249074d3f54690461278c4939f");
		                    //74dbc7249074d3f54690461278c4939f
		map.put("sessionKey", "8bf7b1b9-fe9b-7b4d-15b1-945150964f7e");
		
		RequestContext ctx = new RequestContext(map);
		Response r = ctx.handleRequest();
		System.out.println(r.getXmlResponse());
	}
}

//<iframe class="" height="2485" id="game_iframe" scrolling="no" src="http://bso.ochospace.com/start.php?api_domain=www.friendster.com&amp;api_key=6d014cc55fec6f7fc106bdbda12e7ec0&amp;expires=1329802994&amp;instance_id=1&amp;lang=en&amp;nonce=1329716594.393304&amp;sandbox=false&amp;session_key=8bf7b1b9-fe9b-7b4d-15b1-945150964f7e&amp;src=canvas&amp;user_id=200636452&amp;signed_keys=api_domain,api_key,expires,instance_id,lang,nonce,sandbox,session_key,src,user_id,signed_keys&amp;sig=af70d7143864011f91dcc1c29e3c8857" width="760"></iframe>