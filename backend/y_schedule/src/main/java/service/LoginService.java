package service;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import beans.User_TableBeans;
import daos.User_TableBeansImpl;

public class LoginService {

	private static Logger logger = Logger.getLogger(LoginService.class);

	public static JSONObject validateLogin(JSONObject inputObj) {

		JSONObject tempo = new JSONObject();
		
		String username = inputObj.getString("username");
		String password = inputObj.getString("password");

		User_TableBeans bean = new User_TableBeansImpl().getSecLvlByUsernameAndPassword(username, password);

		if (bean != null) {
			if (password.equals(bean.getUser_password())) {

				tempo.put("userid", bean.getUser_id());
				tempo.put("result", "success");
				tempo.put("role", EmployeeService.getUserBySecLev(bean));
				
				return tempo;
			}
		}

		tempo.put("result", "failure");
		return tempo;

	}

}
