import java.util.HashMap;
import java.util.Map;

import com.friendster.api.client.RequestContext;
import com.friendster.api.client.response.Response;

public class Main {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("uids", "200000230");
		map.put("apiKey", "6d014cc55fec6f7fc106bdbda12e7ec0");
		map.put("apiSecret", "74dbc7249074d3f54690461278c4939f");
		map.put("sessionKey", "f4a7381e-2046-807f-d7f9-0d16783a4723");
		RequestContext ctx = new RequestContext(map);
		Response r = ctx.handleRequest();
		//System.out.println(r.getXmlResponse());
	}
}