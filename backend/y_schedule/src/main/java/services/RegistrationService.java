package services;

import org.json.JSONObject;

import beans.UserBean;
import daos.RegistrationDao;

public class RegistrationService {
	private RegistrationDao impl = new RegistrationDao();

	public JSONObject CreateNewEmployee(String firstname, String lastname, String username, String password,
			Integer storeid) {
		
		JSONObject json = new JSONObject();
		UserBean b = impl.createNewEmployee(username, lastname, password, firstname, storeid);

		if (b != null) {
			json.put("result", "success");
			new EmployeeService().setDefaultAvailability(b.getUser_id());
		}else
			json.put("result", "failure");
		
		return json;

	}

	public RegistrationDao getImpl() {
		return impl;
	}

	public void setImpl(RegistrationDao impl) {
		this.impl = impl;
	}

}
