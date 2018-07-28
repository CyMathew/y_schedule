package services;

import beans.UserBean;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import daos.UserDao;
import services.LoginService;
import services.UserService;

public class ManagerService {
	private static Logger logger = Logger.getLogger(LoginService.class);

	public static JSONObject selectEmpInfo(JSONObject inputObj, Integer userId) {
		UserBean bean = new UserDao().getUserById(userId);
		JSONObject tempo = new JSONObject();

		
		logger.info("select by id: " + userId);
		
		if (bean != null) {
			tempo.put("userid", bean.getUser_id());
			tempo.put("userfname", bean.getUser_fname());
			tempo.put("userlname", bean.getUser_lname());
			tempo.put("seclvl", UserService.getRoleName(bean));
		}
		return tempo;
	}

}
