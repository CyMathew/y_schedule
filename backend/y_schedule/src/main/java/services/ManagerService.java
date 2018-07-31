package services;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import beans.UserBean;
import daos.ManagerDao;
import daos.UserDao;
import util.DateTimeHelper;

public class ManagerService {
	private static Logger logger = Logger.getLogger(ManagerService.class);


	public static JSONObject selectEmpInfo(Integer userId) {
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
	private static JSONObject selectScheduledTimesByWeek(Timestamp startDate, Timestamp endDate, Integer storeId) {
		List bean = new ManagerDao().getScheduleByStoreId(storeId,startDate, endDate);
		JSONObject tempo = new JSONObject();
		tempo.put("userid", bean.get(0));
		tempo.put("storeId", bean.get(1));
		tempo.put("start", bean.get(2));
		tempo.put("end", bean.get(3));
		logger.info("JSON Object Created: " + bean);
		return tempo;
	}
	
	private static JSONObject scheduleEmployee(Integer id) {
		List bean = new ManagerDao().getScheduleByEmployee(id);
		JSONObject tempo = new JSONObject();
		tempo.put("userid", bean.get(0));
		tempo.put("storeId", bean.get(1));
		tempo.put("start", bean.get(2));
		tempo.put("end", bean.get(3));
		logger.info("JSON Object Created: " + bean);
		return tempo;

	}

	public static JSONObject selectScheduledTimesByWeek(Integer storeId, JSONObject parameters) {
		
		Integer week = Integer.parseInt(parameters.getString("week"));
		
		return selectScheduledTimesByWeek(storeId, week);
	}
	
	private static JSONObject selectScheduledTimesByWeek(Integer storeId, Integer week) {
		
		JSONObject schedule = new JSONObject();
		JSONArray employees = new JSONArray();
		JSONObject employee;
		JSONObject shift;
		JSONObject shifts = null;
		int start, end;
		
		schedule.put("dates", DateTimeHelper.getWeekDates(week));
		
		for(int i = 0; i < 5; i++) {
			employee = new JSONObject();
			employee.put("name", "emp"+i);
			shifts = new JSONObject();
			
			for(int j = 1; j <= 7; j++) {
				if(Math.random()*7 < 2)
					continue;
				shift = new JSONObject();
				start = (int)Math.floor(Math.random()*6 + 6);
				end = start + (int)Math.floor(Math.random()*4 + 4);
				shift.put("day", j);
				shift.put("start", start);
				shift.put("end", end);
				shifts.put(""+j, shift);
			}
			employee.put("hours", ""+0);
			employee.put("shifts", shifts);
			employees.put(employee);
		}
		
		schedule.put("employees", employees);
		
		
		return schedule;
	}
	
	public static JSONObject setScheduleEmployee(JSONObject jsonObject) {
		Integer userId = Integer.parseInt(jsonObject.getString("userId"));
		String startTime = jsonObject.getString("startTime");
		String endTime = jsonObject.getString("endTime");
		String date = jsonObject.getString("date");
		
		return setScheduleEmployee(userId, startTime, endTime, date);
	}
	
	private static JSONObject setScheduleEmployee(Integer userId, 
			String startTime, String endTime, String date) {
		
		Timestamp startTs = DateTimeHelper.getTimestamp(date, startTime);
		Timestamp endTs = DateTimeHelper.getTimestamp(date, endTime);
		
		UserBean b = new UserDao().getUserById(userId);
		
		new ManagerDao().createScheduleTimeBean(0, startTs, endTs, b);
		
		JSONObject schedule = new JSONObject();
		
		
		return schedule;
	}
	
	
}
