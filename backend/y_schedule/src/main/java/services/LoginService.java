package services;

import beans.UserBean;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import daos.UserDao;

public class LoginService {

	private static Logger logger = Logger.getLogger(LoginService.class);

	public static JSONObject validateLogin(JSONObject inputObj) {

		JSONObject tempo = new JSONObject();
		
		String username = inputObj.getString("username");
		String password = inputObj.getString("password");

		UserBean bean = new UserDao().getSecLvlByUsernameAndPassword(username, password);

		if (bean != null) {
			if (password.equals(bean.getUser_password())) {

				tempo.put("userid", "" + bean.getUser_id());
				tempo.put("result", "success");
				tempo.put("role", UserService.getRoleName(bean));
				
				return tempo;
			}
		}

		tempo.put("result", "failure");
		return tempo;

	}

}
