package services;

import beans.UserBean;
import org.apache.log4j.Logger;

public class UserService {
	public static String getRoleName(UserBean bean){
		Logger logger = Logger.getLogger(UserService.class);
		logger.info(bean.getSec_lvl());
		switch (bean.getSec_lvl()) {
		case 1:
			logger.info("Case 1" + bean);
			return "employee";
		case 2:
			logger.info("Case 2" + bean);
			return "manager";
		case 3:
			logger.info("Case 3" + bean);
			return "coordinator";
		}
		logger.info("Case Does not Exist" + bean);
		return "failure";
	}

	public static String getUserName(UserBean user) {
		
		return user.getUser_fname() + " " + user.getUser_lname();
	}
}
