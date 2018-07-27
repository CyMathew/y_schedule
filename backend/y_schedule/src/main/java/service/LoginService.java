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

				switch (bean.getSec_lvl()) {
				case 1:
					tempo.put("role", "employee");
					logger.info("Case 1" + tempo);
					return tempo;
				case 2:
					tempo.put("role", "manager");
					logger.info("Case 2" + tempo);
					return tempo;
				case 3:
					tempo.put("role", "coordinator");
					logger.info("Case 3" + tempo);
					return tempo;
				}
			}
		}

		tempo.put("result", "failure");
		return tempo;

	}

}
