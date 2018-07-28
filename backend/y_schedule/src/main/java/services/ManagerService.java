package services;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import beans.UserBean;
import daos.ManagerDao;
import daos.UserDao;

public class ManagerService {
	private static Logger logger = Logger.getLogger(ManagerService.class);

	public static JSONObject selectEmpInfo(JSONObject inputObj, Integer userId) {
		UserBean bean = new UserDao().getUserById(userId);
		JSONObject tempo = new JSONObject();

		logger.info("select by id: " + userId);

		if (bean != null) {
			tempo.put("userid", bean.getUser_id());
			tempo.put("userfname", bean.getUser_fname());
			tempo.put("userlname", bean.getUser_lname());
			tempo.put("seclvl", UserService.getRoleName(bean));
			tempo.put("storeId", bean.getStore_Id());
		}
		
		return tempo;
	}

	/**
	 * Json object containing scheduled times for a week organized by employee 
	 * @param weekStart
	 * @param storeId
	 * @return
	 */
	public static JSONObject selectScheduledTimesByWeek(Timestamp weekStart, Integer storeId) {
		List bean = new ManagerDao().getScheduleByStoreId(storeId);
		JSONObject tempo = new JSONObject();
		
		tempo.put("userid", bean.get(0));
		tempo.put("storeId", bean.get(1));
		tempo.put("start", bean.get(2));
		tempo.put("end", bean.get(3));
		
		return tempo;
	}

	public static JSONObject selectAvailableEmployeesByDay(Timestamp weekStart, Integer storeId, String day) {

		return null;
	}
	
	public static JSONObject selectAvailableEmployeesByDay(Timestamp weekStart, Integer storeId, String day, Integer department) {

		return null;
	}
	
	public static Boolean scheduleEmployee(Integer id, Timestamp timeStart, Timestamp timeEnd) {
		
		return null;
	}
}
