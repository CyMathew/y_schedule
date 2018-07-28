package util;

import org.json.JSONObject;

public class ParsedRequest {
	private Integer userId;
	private String userRole;
	private String userName;
	private Integer storeId;

	private JSONObject parameters;

	public ParsedRequest(JSONObject sessionData, JSONObject parameters) {
		if (sessionData.getString("userid") != null)
			this.userId = Integer.parseInt(sessionData.getString("userid"));
		//if (sessionData.getString("storeid") != null)
		//	this.storeId = Integer.parseInt(sessionData.getString("storeid"));
		this.storeId = 2367;
		this.userName = sessionData.getString("username");
		this.userRole = sessionData.getString("userrole");		
		this.parameters = parameters;
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
	
	
}
