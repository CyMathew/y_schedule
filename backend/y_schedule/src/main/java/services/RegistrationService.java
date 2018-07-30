package services;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import beans.UserBean;
import daos.RegistrationDao;
import daos.UserDao;

public class RegistrationService {
	private RegistrationDao impl = new RegistrationDao();
	private EmployeeService es = new EmployeeService();
	
	public void setEs(EmployeeService es) {
		this.es = es;
	}

	final static Logger logger = Logger.getLogger(RegistrationService.class);

	public JSONObject CreateNewEmployee(String firstname, String lastname, String username, String password,
			Integer storeid) {

		JSONObject json = new JSONObject();
		UserBean b = impl.createNewEmployee(username, lastname, password, firstname, storeid);

		if (b != null) {
			logger.info("Employee registered: " + b);
			json.put("result", "success");

			es.setDefaultAvailability(b);

		} else {
			logger.info("Failed to register new employee");
			json.put("result", "failure");
		}

		return json;

	}

	public RegistrationDao getImpl() {
		return impl;
	}

	public void setImpl(RegistrationDao impl) {
		this.impl = impl;
	}

}
