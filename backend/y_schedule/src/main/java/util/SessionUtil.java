package util;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

public class SessionUtil {

	public static JSONObject getOriginalRequest(HttpServletRequest request) {
		return (JSONObject) request.getSession().getAttribute("request");
	}
	
	public static ParsedRequest getParsedRequest(HttpServletRequest request) {
		JSONObject json = (JSONObject) request.getSession().getAttribute("request");
		return new ParsedRequest(json);
	}

}
