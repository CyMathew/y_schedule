package services;

import org.json.JSONObject;

import daos.RegistrationDao;

public class RegistrationService {
	private RegistrationDao impl = new RegistrationDao();
	public JSONObject CreateNewEmployee(String firstname, String lastname, String username, String password) {
		JSONObject json = new JSONObject();
		json.put("result", impl.createNewEmployee(username, lastname, password, firstname));
		return json;
		
	}
	
	
	public RegistrationDao getImpl() {
		return impl;
	}
	public void setImpl(RegistrationDao impl) {
		this.impl = impl;
	}
	
	

}
