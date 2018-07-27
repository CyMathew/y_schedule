package service;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import beans.User_TableBeans;
import daos.User_TableBeansImpl;

public class ManagerService {
	private static Logger logger = Logger.getLogger(LoginService.class);

	public static JSONObject validateLogin(JSONObject inputObj) {
		User_TableBeans bean = new User_TableBeans();
		JSONObject tempo = new JSONObject();

		if (bean != null) {
			tempo.put("userid", bean.getUser_id());
			tempo.put("userfname", bean.getUser_fname());
			tempo.put("userlname", bean.getUser_lname());
			tempo.put("seclvl", EmployeeService.getUserBySecLev(bean)); // come back later to set to a string 
		}
		return inputObj;
	}

}
