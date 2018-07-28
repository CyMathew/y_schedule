package services;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import beans.EmployeeAvailabilityBean;
import beans.UserBean;
import daos.EmployeeDao;
import daos.UserDao;

public class EmployeeService {
	private static EmployeeDao ed = new EmployeeDao();
	private static JSONObject reply = new JSONObject();
	private static UserBean userbean = new UserBean();
	private static UserDao ud = new UserDao();
	
	
/**
 * This takes the JSON object and parses the request to see what action it should take
 * */
	@SuppressWarnings("unused")
	public JSONObject parseRequest(JSONObject json, Integer id) {
		JSONArray args = json.getJSONArray("availDetails");
		userbean = ud.getUserById(id);
		
		if(json != null) {
			switch((String)json.get("action")) {
			
			case "editAvailDetails": return editAvailDetails(args, userbean);
									
			case "getAvailDetails": return getAvailableById(id);
			
			default: return reply.put("result", "failure");
			}}		
		
		else {return reply.put("result", "failure");}
		
	}
	
	/**
	 * This deletes all old records from the database and updates it with the new availability for the employee
	 * */
	public JSONObject editAvailDetails(JSONArray jsonarray, UserBean id) {
		JSONObject jsonstore = new JSONObject();
		JSONObject timestore = new JSONObject();
		JSONArray timearray = new JSONArray();
		String day = null;
		String start = null;
		String end = null;
		
		if(ed.removeAllReguests(id.getUser_id())) {
			for(int i = 0; i < jsonarray.length(); i ++) {
				jsonstore = jsonarray.getJSONObject(i);
				day = jsonstore.getString("day");
				timearray = jsonstore.getJSONArray("times");
				
				for(int l = 0; l <= timearray.length(); l++) {
					timestore = timearray.getJSONObject(l);
					start = timestore.getString("startTime");
					end = timestore.getString("endTime");
					day = timestore.getString("day");
					if((ed.updateRequests(start, end, day, id)).equals("failure")) {
						reply.put("result", "failure");
					}
					
				}
			}
			reply.put("result", "success");
			return reply;}
		
		else {
			reply.put("result", "failure");
			return reply;
			}
	}
	
	/*
	public JSONObject getAvailByDate (String day) {
		JSONObject weekdetails = new JSONObject();
		JSONArray  weekarray   = new JSONArray();
		JSONObject capsule = new JSONObject();
		JSONArray timearray = new JSONArray();
		List list = ed.getStartTimesById(day);
		
		if(list.size() <=0) {
			capsule.put("day", "none available");
			capsule.put("times", "none available");
			weekarray.put(0,capsule);
			weekdetails.put("availDetails", weekarray);
			return weekdetails;
		}
		
		for(int i=0; i <=list.size();i++) {
			timearray.put(new JSONObject().put("startTime", ((EmployeeAvailabilityBean)list.get(i)).getStart())
					                      .put("endTime",   ((EmployeeAvailabilityBean)list.get(i)).getEnd())
					                      );

		}
		weekdetails.put("weekdetails", weekarray);
		
		return weekdetails;
	}
	*/
	
	public JSONObject getAvailableById(Integer id) {
		JSONObject weekdetails    = new JSONObject();
		JSONObject capsule		  = new JSONObject();
		JSONObject jsonmonday     = new JSONObject();
		JSONObject jsontuesday    = new JSONObject();
		JSONObject jsonwednesday  = new JSONObject();
		JSONObject jsonthursday   = new JSONObject();
		JSONObject jsonfriday     = new JSONObject();
		JSONObject jsonsaturday   = new JSONObject();
		JSONObject jsonsunday     = new JSONObject();
		JSONArray  weekarray      = new JSONArray();
		JSONArray  jsonAmonday    = new JSONArray();
		JSONArray  jsonAtuesday   = new JSONArray();
		JSONArray  jsonAwednesday = new JSONArray();
		JSONArray  jsonAthursday  = new JSONArray();
		JSONArray  jsonAfriday    = new JSONArray();
		JSONArray  jsonAsaturday  = new JSONArray();
		JSONArray  jsonAsunday    = new JSONArray();


		List list = ed.getStartTimesById(id);
		
		if(list.size() <=0) {
			capsule.put("day", "none available");
			capsule.put("times", "none available");
			weekarray.put(0,capsule);
			weekdetails.put("availDetails", weekarray);
			return weekdetails;
		}
		/*
		  		(((EmployeeAvailabilityBean)list.get(i)).getStart());
				(((EmployeeAvailabilityBean)list.get(i)).getEnd());
		 * */

		for(int i=0; i <= list.size(); i++) {
			if(((EmployeeAvailabilityBean)list.get(i)).getDay().equals("monday")){
				jsonAmonday.put(new JSONObject().put("startTime", (((EmployeeAvailabilityBean)list.get(i)).getStart())).put("endTime"  , (((EmployeeAvailabilityBean)list.get(i)).getEnd())));
			}
			else if(((EmployeeAvailabilityBean)list.get(i)).getDay().equals("tuesday")){
				jsonAtuesday.put(new JSONObject().put("startTime", (((EmployeeAvailabilityBean)list.get(i)).getStart())).put("endTime"  , (((EmployeeAvailabilityBean)list.get(i)).getEnd())));
			}
			else if(((EmployeeAvailabilityBean)list.get(i)).getDay().equals("wednesday")){
				jsonAwednesday.put(new JSONObject().put("startTime", (((EmployeeAvailabilityBean)list.get(i)).getStart())).put("endTime"  , (((EmployeeAvailabilityBean)list.get(i)).getEnd())));
			}
			else if(((EmployeeAvailabilityBean)list.get(i)).getDay().equals("thursday")){
				jsonAthursday.put(new JSONObject().put("startTime", (((EmployeeAvailabilityBean)list.get(i)).getStart())).put("endTime"  , (((EmployeeAvailabilityBean)list.get(i)).getEnd())));
			}
			else if(((EmployeeAvailabilityBean)list.get(i)).getDay().equals("friday")){
				jsonAfriday.put(new JSONObject().put("startTime", (((EmployeeAvailabilityBean)list.get(i)).getStart())).put("endTime"  , (((EmployeeAvailabilityBean)list.get(i)).getEnd())));
			}
			else if(((EmployeeAvailabilityBean)list.get(i)).getDay().equals("saturday")){
				jsonAsaturday.put(new JSONObject().put("startTime", (((EmployeeAvailabilityBean)list.get(i)).getStart())).put("endTime"  , (((EmployeeAvailabilityBean)list.get(i)).getEnd())));
			}
			else if(((EmployeeAvailabilityBean)list.get(i)).getDay().equals("sunday")){
				jsonAsunday.put(new JSONObject().put("startTime", (((EmployeeAvailabilityBean)list.get(i)).getStart())).put("endTime"  , (((EmployeeAvailabilityBean)list.get(i)).getEnd())));
			}
		}
		
		jsonsunday.put("day", "sunday").put("times", jsonAsunday);
		jsonmonday.put("day", "monday").put("times", jsonAmonday);
		jsontuesday.put("day", "tuesday").put("times", jsonAtuesday);
		jsonwednesday.put("day", "wednesday").put("times", jsonAwednesday);
		jsonthursday.put("day", "thursday").put("times", jsonAthursday);
		jsonfriday.put("day", "friday").put("times", jsonAfriday);
		jsonsaturday.put("day", "saturday").put("times", jsonAsaturday);
		
		weekarray.put(jsonsunday);
		weekarray.put(jsonmonday);
		weekarray.put(jsontuesday);
		weekarray.put(jsonwednesday);
		weekarray.put(jsonthursday);
		weekarray.put(jsonfriday);
		weekarray.put(jsonsaturday);

		weekdetails.put("availDetails", weekarray);
		
		return weekdetails;
	}

}
