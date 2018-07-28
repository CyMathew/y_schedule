package services;

import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import beans.UserBean;
import daos.UserDao;

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

	/**
	 * Json object containing scheduled times for a week organized by employee 
	 * @param weekStart
	 * @param storeId
	 * @return
	 */
	public static JSONObject selectScheduledTimesByWeek(Timestamp weekStart, Integer storeId) {

		return null;
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

	public static JSONObject selectScheduledTimesByWeek(JSONObject parameters) {
		
		JSONObject schedule = new JSONObject();
		
		JSONObject shift;
		JSONObject shifts = null;
		int start, end;
		
		for(int i = 0; i < 5; i++) {
			shifts = new JSONObject();
			for(int j = 0; j < 7; j++) {
				if(Math.random()*7 < 2)
					continue;
				shift = new JSONObject();
				start = (int)Math.floor(Math.random()*6 + 6);
				end = start + (int)Math.floor(Math.random()*4 + 4);
				shift.put("day", i);
				shift.put("start", start);
				shift.put("end", end);
				shifts.put(""+j, shift);
			}
			schedule.put("emp"+i, shifts);
		}
		
		return schedule;
	}
}
