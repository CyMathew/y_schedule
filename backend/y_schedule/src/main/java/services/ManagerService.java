package services;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public static JSONObject selectScheduledTimesByWeek(Timestamp startDate, Timestamp endDate, Integer storeId) {
		List bean = new ManagerDao().getScheduleByStoreId(storeId,startDate, endDate);
		JSONObject tempo = new JSONObject();
		tempo.put("userid", bean.get(0));
		tempo.put("storeId", bean.get(1));
		tempo.put("start", bean.get(2));
		tempo.put("end", bean.get(3));
		logger.info("JSON Object Created: " + bean);
		return tempo;
	}

	public static JSONObject selectAvailableEmployeesByDay(Timestamp weekStart, Integer storeId, String day) {

		return null;
	}
	
	public static JSONObject selectAvailableEmployeesByDay(Timestamp weekStart, Integer storeId, String day, Integer department) {

		return null;
	}
	
	public static JSONObject scheduleEmployee(Integer id) {
		List bean = new ManagerDao().getScheduleByEmployee(id);
		JSONObject tempo = new JSONObject();
		tempo.put("userid", bean.get(0));
		tempo.put("storeId", bean.get(1));
		tempo.put("start", bean.get(2));
		tempo.put("end", bean.get(3));
		logger.info("JSON Object Created: " + bean);
		return tempo;

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
	
	public static JSONObject setScheduleEmployee(Integer userId, 
			String startTime, String endTime, String date) {
		JSONObject schedule = new JSONObject();
		
		
		return schedule;
	}
	
	
}
