package service;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import beans.User_TableBeans;

public class EmployeeService {
	public static String getUserBySecLev(User_TableBeans bean){
		Logger logger = Logger.getLogger(EmployeeService.class);
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
}
