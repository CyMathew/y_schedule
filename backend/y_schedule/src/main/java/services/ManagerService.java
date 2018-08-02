package services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import beans.EmployeeAvailabilityBean;
import beans.ScheduleTimeBean;
import beans.UserBean;
import daos.EmployeeDao;
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
	 * 
	 * @param weekStart
	 * @param storeId
	 * @return
	 */
	// private static JSONObject selectScheduledTimesByWeek(Timestamp startDate,
	// Timestamp endDate, Integer storeId) {
	// List bean = new ManagerDao().getScheduleByStoreId(storeId,startDate,
	// endDate);
	// JSONObject tempo = new JSONObject();
	// tempo.put("userid", bean.get(0));
	// tempo.put("storeId", bean.get(1));
	// tempo.put("start", bean.get(2));
	// tempo.put("end", bean.get(3));
	// logger.info("JSON Object Created: " + bean);
	// return tempo;
	// }

	// private static JSONObject scheduleEmployee(Integer id) {
	// List bean = new ManagerDao().getScheduleByEmployee(id);
	// JSONObject tempo = new JSONObject();
	// tempo.put("userid", bean.get(0));
	// tempo.put("storeId", bean.get(1));
	// tempo.put("start", bean.get(2));
	// tempo.put("end", bean.get(3));
	// logger.info("JSON Object Created: " + bean);
	// return tempo;
	//
	// }

	public static JSONObject selectScheduledTimesByWeek(Integer storeId, JSONObject parameters) {

		Integer week = Integer.parseInt(parameters.getString("week"));

		return selectScheduledTimesByWeek(storeId, week);
	}

	public static JSONObject selectScheduledTimesByWeek(Integer storeId, Integer week) {

		JSONObject schedule = new JSONObject();
		JSONArray employees = new JSONArray();
		JSONObject employee;

		Timestamp start = DateTimeHelper.getWeekStartByOffset(week);
		Timestamp end = DateTimeHelper.getWeekEnd(start);

		List<ScheduleTimeBean> times = new ManagerDao().getScheduleByStoreId(storeId, start, end);

		schedule.put("dates", DateTimeHelper.getWeekDates(week));

		HashMap<UserBean, JSONObject> employeeMap = new HashMap<>();

		for (ScheduleTimeBean b : times) {
			UserBean user = b.getUser();
			JSONObject shifts;
			if (employeeMap.containsKey(user))
				shifts = employeeMap.get(user);
			else {
				shifts = new JSONObject();
				employeeMap.put(user, shifts);
			}
			JSONObject shift = new JSONObject();
			int day = DateTimeHelper.TimestampGetDay(b.getStart());
			shift.put("day", day);
			shift.put("start", DateTimeHelper.TimestampToTimeFloat(b.getStart()));
			shift.put("end", DateTimeHelper.TimestampToTimeFloat(b.getEnd()));
			shifts.put("" + day, shift);
		}
		
		for(UserBean user : employeeMap.keySet()) {
			employee = new JSONObject();
			employee.put("hours", ""+0);
			employee.put("shifts", employeeMap.get(user));
			employee.put("name", user.getUser_fname()+ " " + user.getUser_lname());
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

	private static JSONObject setScheduleEmployee(Integer userId, String startTime, String endTime, String date) {

		Timestamp startTs = DateTimeHelper.getTimestamp(date, startTime);
		Timestamp endTs = DateTimeHelper.getTimestamp(date, endTime);

		UserBean b = new UserDao().getUserById(userId);

		new ManagerDao().createScheduleTimeBean(0, startTs, endTs, b);

		JSONObject schedule = new JSONObject();

		return schedule;
	}
	
	public static JSONObject getEmployeeAvailabilityByDay(JSONObject jsonObject) {
		Integer id = Integer.parseInt(jsonObject.getString("userId"));
		Integer day = Integer.parseInt(jsonObject.getString("day"));
		
		return getEmployeeAvailabilityByDay(id, day);
		
	}
	
	private static JSONObject getEmployeeAvailabilityByDay(Integer id, Integer day) {
		
		JSONObject obj = new JSONObject();
		JSONArray times = new JSONArray();
		
		List<EmployeeAvailabilityBean> ls = new EmployeeDao().getAvailableTimesByDay(id, DateTimeHelper.getDayOfWeekName(day));
		
		for(EmployeeAvailabilityBean b : ls) {
			JSONObject time = new JSONObject();
			time.put("start", b.getStart());
			time.put("end", b.getEnd());
			times.put(time);
		}
		
		obj.put("times", times);
		
		return obj;
	}

}
