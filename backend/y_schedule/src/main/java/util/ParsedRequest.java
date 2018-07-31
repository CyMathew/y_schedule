package util;

import org.json.JSONObject;

public class ParsedRequest {
	private Integer userId;
	private String userRole;
	private String userName;
	private Integer storeId;
	private String action;

	private JSONObject parameters;
	
	public ParsedRequest(JSONObject request) {
		
		JSONObject sessionData = request.getJSONObject("sessionData");
    	JSONObject parameters = request.getJSONObject("param");
		
		if (sessionData.getString("userid").length() != 0) {
			this.userId = Integer.parseInt(sessionData.getString("userid"));
			this.userName = sessionData.getString("username");
			this.userRole = sessionData.getString("userrole");	
			this.storeId = 2367;
		}

		this.parameters = parameters;
		this.action = parameters.getString("action");
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public JSONObject getParameters() {
		return parameters;
	}

	public void setParameters(JSONObject parameters) {
		this.parameters = parameters;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return "ParsedRequest [userId=" + userId + ", userRole=" + userRole + ", userName=" + userName + ", storeId="
				+ storeId + ", action=" + action + ", parameters=" + parameters + "]";
	}
	
	
	
}
