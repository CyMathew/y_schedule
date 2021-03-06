package services;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashSet;

//import static org.mockito.Matchers.intThat;

import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import beans.EmployeeAvailabilityBean;
import beans.ScheduleTimeBean;
import beans.UserBean;
import daos.EmployeeDao;
import daos.UserDao;
import util.DateTimeHelper;


public class EmployeeService {

	private EmployeeDao ed = new EmployeeDao();
	private JSONObject reply = new JSONObject();
	private UserBean userbean = new UserBean();
	private UserDao ud = new UserDao();

	/**
	 * This takes the JSON object and parses the request to see what action it
	 * should take
	 */
	@SuppressWarnings("unused")
	public JSONObject parseRequest(JSONObject json, Integer id) {
		// JSONArray args = json.getJSONArray("getAvailDetails");
		userbean = ud.getUserById(id);

		if (json != null) {
			switch ((String) json.get("action")) {

			case "editAvailDetails":
				return editAvailDetails(json.getJSONArray("availDetails"), userbean);
			case "getAvailDetails":
				return getAvailableById(id);
			case "getWeekSchedule":
				return selectScheduledTimesByWeek(id, json.getInt("weekOffset"));
			default:
				return reply.put("result", "failure");
			}
		}

		return reply.put("result", "failure");
	}

	/**
	 * This just adds new times pending for approval and inserts them into the
	 * availability table. 
	 */
	public JSONObject editAvailDetails(JSONArray jsonarray, UserBean id) {
		// JSONObject jsonstore = new JSONObject();
		// JSONObject timestore = new JSONObject();
		// JSONArray timearray = new JSONArray();
		String day = null;
		float start;
		float end;

		
			for (int i = 0; i < jsonarray.length(); i++) {
				day = jsonarray.getJSONObject(i).getString("day");
				end = Float.parseFloat(jsonarray.getJSONObject(i).get("endTime").toString());
				start = Float.parseFloat(jsonarray.getJSONObject(i).get("startTime").toString());
				if ((ed.updateRequests(start, end, day, id)).equals("failure")) {
					reply.put("result", "failure");
					return reply;
				}
		}
		reply.put("result", "success");
		return reply;

	}
	
	/**
	 * Sets the default times for an employee
	 **/
	public void setDefaultAvailability(UserBean user) {

		float start = 9f;
		float end = 17f;

		ed.removeAllReguests(user.getUser_id());

		for (int i = 1; i < 6; i++)
			ed.updateDefaultRequests(start, end, DateTimeHelper.getDayOfWeekName(i), user);
	}

	/**
	 * Get all shifts for employee with userID
	 */
	public JSONObject getAvailableById(Integer id) {
		JSONObject weekdetails = new JSONObject();
		JSONArray weekarray = new JSONArray();
		List<EmployeeAvailabilityBean> list = ed.getStartTimesById(id);
		for (int i = 0; i < list.size(); i++) {
			weekarray.put(new JSONObject().put("day", list.get(i).getDay()).put("startTime", list.get(i).getStart())
					.put("endTime", list.get(i).getEnd()));
		}
		weekdetails.put("weekDetails", weekarray);
		return weekdetails;
	}

	public void setEd(EmployeeDao ed) {
		this.ed = ed;
	}

	public EmployeeDao getEd() {
		return this.ed;
	}

	public static JSONObject getAvailableEmployeesOnDay(JSONObject parameters) {

		String weekday = parameters.getString("day");

		return getAvailableEmployeesOnDay(weekday);
	}

	private static JSONObject getAvailableEmployeesOnDay(String weekday) {
		JSONObject jsonObj = new JSONObject();
		JSONArray availEmployees = new JSONArray();

		List<EmployeeAvailabilityBean> list = new EmployeeDao().getStartTimesByDay(weekday);
		Set<Integer> users = new HashSet<>();
		
		for (EmployeeAvailabilityBean b : list) {	
			//check if the user has already been added
			Integer userId = b.getUser().getUser_id();
			if(users.contains(userId))
				continue;
			
			JSONObject temp = new JSONObject();			
			temp.put("userId", userId);
			temp.put("name", b.getUser().getUser_fname() + " " + b.getUser().getUser_lname());
			users.add(userId);
			availEmployees.put(temp);
		}

		jsonObj.put("availEmployees", availEmployees);

		return jsonObj;
	}

	public boolean getEmployeeAvailableForRange(Integer userId, Timestamp startTime, Timestamp endTime) {

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		float strStart = DateTimeHelper.TimeToFloat(sdf.format(startTime));
		float strEnd = DateTimeHelper.TimeToFloat(sdf.format(endTime));
		
		
		String weekday = startTime.toLocalDateTime().getDayOfWeek().name().toLowerCase();

		List ls = ed.getEmployeeAvailableForRange(userId, weekday, strStart, strEnd);
		
		System.out.println(strStart);
		System.out.println(strEnd);
		System.out.println(weekday);
		
		System.out.println(ls);
		return (ls.size() > 0);
		
	}
	
	public JSONObject selectScheduledTimesByWeek(Integer empId, Integer week) {

		JSONObject schedule = new JSONObject();
		JSONObject employee;
		
		UserBean user = new UserDao().getUserById(empId);
		
		Timestamp start = DateTimeHelper.getWeekStartByOffset(week);
		Timestamp end = DateTimeHelper.getWeekEnd(start);

		List<ScheduleTimeBean> times = new EmployeeDao().getScheduleByEmployee(empId, start, end);

		schedule.put("dates", DateTimeHelper.getWeekDates(week));

		JSONArray shifts = new JSONArray();
		
		for (ScheduleTimeBean b : times) {
			JSONObject shift = new JSONObject();
			int day = DateTimeHelper.TimestampGetDay(b.getStart());
			shift.put("day", DateTimeHelper.getDayOfWeekName(day));
			shift.put("startTime", DateTimeHelper.TimestampToTimeFloat(b.getStart()));
			shift.put("endTime", DateTimeHelper.TimestampToTimeFloat(b.getEnd()));
			shifts.put(shift);
		}
		
		employee = new JSONObject();
		employee.put("hours", ""+0);
		employee.put("shifts", shifts);
		employee.put("name", user.getUser_fname()+ " " + user.getUser_lname());

		schedule.put("employee", employee);

		return schedule;
	}

}

// LEGACY CODE
/*
 * if(ed.removeAllReguests(id.getUser_id())) { for(int i = 0; i <
 * jsonarray.length(); i ++) { jsonstore = jsonarray.getJSONObject(i); day =
 * jsonstore.getString("day"); timearray = jsonstore.getJSONArray("times");
 * 
 * for(int l = 0; l < timearray.length(); l++) { timestore =
 * timearray.getJSONObject(l); start = timestore.getString("startTime"); end =
 * timestore.getString("endTime"); if((ed.updateRequests(start, end, day,
 * id)).equals("failure")) { reply.put("result", "failure"); return reply; }
 * 
 * } } reply.put("result", "success"); return reply;}
 * 
 * else { reply.put("result", "failure"); return reply; }
 */

/*
 * if(list.size() <=0) { capsule.put("day", "none available");
 * capsule.put("times", "none available"); weekarray.put(0,capsule);
 * weekdetails.put("availDetails", weekarray); return weekdetails; }
 */
/*
 * (((EmployeeAvailabilityBean)list.get(i)).getStart());
 * (((EmployeeAvailabilityBean)list.get(i)).getEnd());
 */

/*
 * for(int i=0; i < list.size(); i++) {
 * if(((EmployeeAvailabilityBean)list.get(i)).getDay().equals("monday")){
 * jsonAmonday.put(new JSONObject().put("startTime",
 * (((EmployeeAvailabilityBean)list.get(i)).getStart())).put("endTime" ,
 * (((EmployeeAvailabilityBean)list.get(i)).getEnd()))); } else
 * if(((EmployeeAvailabilityBean)list.get(i)).getDay().equals("tuesday")){
 * jsonAtuesday.put(new JSONObject().put("startTime",
 * (((EmployeeAvailabilityBean)list.get(i)).getStart())).put("endTime" ,
 * (((EmployeeAvailabilityBean)list.get(i)).getEnd()))); } else
 * if(((EmployeeAvailabilityBean)list.get(i)).getDay().equals("wednesday")){
 * jsonAwednesday.put(new JSONObject().put("startTime",
 * (((EmployeeAvailabilityBean)list.get(i)).getStart())).put("endTime" ,
 * (((EmployeeAvailabilityBean)list.get(i)).getEnd()))); } else
 * if(((EmployeeAvailabilityBean)list.get(i)).getDay().equals("thursday")){
 * jsonAthursday.put(new JSONObject().put("startTime",
 * (((EmployeeAvailabilityBean)list.get(i)).getStart())).put("endTime" ,
 * (((EmployeeAvailabilityBean)list.get(i)).getEnd()))); } else
 * if(((EmployeeAvailabilityBean)list.get(i)).getDay().equals("friday")){
 * jsonAfriday.put(new JSONObject().put("startTime",
 * (((EmployeeAvailabilityBean)list.get(i)).getStart())).put("endTime" ,
 * (((EmployeeAvailabilityBean)list.get(i)).getEnd()))); } else
 * if(((EmployeeAvailabilityBean)list.get(i)).getDay().equals("saturday")){
 * jsonAsaturday.put(new JSONObject().put("startTime",
 * (((EmployeeAvailabilityBean)list.get(i)).getStart())).put("endTime" ,
 * (((EmployeeAvailabilityBean)list.get(i)).getEnd()))); } else
 * if(((EmployeeAvailabilityBean)list.get(i)).getDay().equals("sunday")){
 * jsonAsunday.put(new JSONObject().put("startTime",
 * (((EmployeeAvailabilityBean)list.get(i)).getStart())).put("endTime" ,
 * (((EmployeeAvailabilityBean)list.get(i)).getEnd()))); } }
 * 
 * jsonsunday.put("day", "sunday").put("times", jsonAsunday);
 * jsonmonday.put("day", "monday").put("times", jsonAmonday);
 * jsontuesday.put("day", "tuesday").put("times", jsonAtuesday);
 * jsonwednesday.put("day", "wednesday").put("times", jsonAwednesday);
 * jsonthursday.put("day", "thursday").put("times", jsonAthursday);
 * jsonfriday.put("day", "friday").put("times", jsonAfriday);
 * jsonsaturday.put("day", "saturday").put("times", jsonAsaturday);
 * 
 * weekarray.put(jsonsunday); weekarray.put(jsonmonday);
 * weekarray.put(jsontuesday); weekarray.put(jsonwednesday);
 * weekarray.put(jsonthursday); weekarray.put(jsonfriday);
 * weekarray.put(jsonsaturday);
 * 
 * JSONArray jsonAmonday = new JSONArray(); JSONArray jsonAtuesday = new
 * JSONArray(); JSONArray jsonAwednesday = new JSONArray(); JSONArray
 * jsonAthursday = new JSONArray(); JSONArray jsonAfriday = new JSONArray();
 * JSONArray jsonAsaturday = new JSONArray(); JSONArray jsonAsunday = new
 * JSONArray();
 * 
 * JSONObject capsule = new JSONObject(); JSONObject jsonmonday = new
 * JSONObject(); JSONObject jsontuesday = new JSONObject(); JSONObject
 * jsonwednesday = new JSONObject(); JSONObject jsonthursday = new JSONObject();
 * JSONObject jsonfriday = new JSONObject(); JSONObject jsonsaturday = new
 * JSONObject(); JSONObject jsonsunday = new JSONObject();
 */
/*
 * public JSONObject getAvailByDate (String day) { JSONObject weekdetails = new
 * JSONObject(); JSONArray weekarray = new JSONArray(); JSONObject capsule = new
 * JSONObject(); JSONArray timearray = new JSONArray(); List list =
 * ed.getStartTimesById(day);
 * 
 * if(list.size() <=0) { capsule.put("day", "none available");
 * capsule.put("times", "none available"); weekarray.put(0,capsule);
 * weekdetails.put("availDetails", weekarray); return weekdetails; }
 * 
 * for(int i=0; i <=list.size();i++) { timearray.put(new
 * JSONObject().put("startTime",
 * ((EmployeeAvailabilityBean)list.get(i)).getStart()) .put("endTime",
 * ((EmployeeAvailabilityBean)list.get(i)).getEnd()) );
 * 
 * } weekdetails.put("weekdetails", weekarray);
 * 
 * return weekdetails; }
 */
