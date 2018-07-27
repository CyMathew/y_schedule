package service;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import beans.User_TableBeans;
import daos.User_TableBeansImpl;

public class ManagerService {
	private static Logger logger = Logger.getLogger(LoginService.class);

	public static JSONObject selectEmpInfo(JSONObject inputObj, Integer userId) {
		User_TableBeans bean = new User_TableBeansImpl().getUserById(userId);
		JSONObject tempo = new JSONObject();

		
		logger.info("select by id: " + userId);
		
		if (bean != null) {
			tempo.put("userid", bean.getUser_id());
			tempo.put("userfname", bean.getUser_fname());
			tempo.put("userlname", bean.getUser_lname());
			tempo.put("seclvl", EmployeeService.getUserBySecLev(bean));  
		}
		return tempo;
	}

}
