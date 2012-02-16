import java.util.HashMap;
import java.util.Map;

import com.friendster.api.client.RequestContext;

public class Main {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("apiKey", "6d014cc55fec6f7fc106bdbda12e7ec0");
		map.put("apiSecret", "74dbc7249074d3f54690461278c4939f");
		map.put("sessionKey", "f4a7381e-2046-807f-d7f9-0d16783a4723");
		map.put("uids", "200636452");
		RequestContext ctx = new RequestContext(map);
		ctx.handleRequest();
	}
}