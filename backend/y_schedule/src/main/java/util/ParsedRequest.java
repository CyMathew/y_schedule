package util;

import org.json.JSONObject;

public class ParsedRequest {
	private Integer userId;
	private JSONObject parameters;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public JSONObject getParameters() {
		return parameters;
	}
	public void setParameters(JSONObject parameters) {
		this.parameters = parameters;
	}
	public ParsedRequest(Integer userId, JSONObject parameters) {
		super();
		this.userId = userId;
		this.parameters = parameters;
	}
	
	
	
}
