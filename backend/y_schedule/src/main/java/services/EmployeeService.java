package services;

import org.json.JSONArray;
import org.json.JSONObject;

import daos.EmployeeDao;

public class EmployeeService {
	private static EmployeeDao ad = new EmployeeDao();
	private static JSONObject reply = new JSONObject();
	
	
/**
 * This takes the JSON object and parses the request to see what action it should take
 * */
	@SuppressWarnings("unused")
	public JSONObject parseRequest(JSONObject json, Integer id) {
		JSONArray args = json.getJSONArray("weekDetails");
		
		if(json != null) {
			switch((String)json.get("action")) {
			case "editWeekDetails": return editWeekDetails(args, id);
									
			case "getWeekDetails": return reply;//TODO make this 
			default: return reply.put("result", "failure");
			}}		
		
		else {return reply.put("result", "failure");}
		
	}
	
	/**
	 * This deletes all old records from the database and updates it with the new availability for the employee
	 * */
	public JSONObject editWeekDetails(JSONArray jsonarray, Integer id) {
		JSONObject jsonstore = new JSONObject();
		JSONObject timestore = new JSONObject();
		JSONArray timearray = new JSONArray();
		String date = null;
		String start = null;
		String end = null;
		String response = null;
		
		if(ad.removeAllReguests(id)) {
			for(int i = 0; i < jsonarray.length(); i ++) {
				jsonstore = jsonarray.getJSONObject(i);
				date = jsonstore.getString("date");
				timearray = jsonstore.getJSONArray("times");
				
				for(int l = 0; l < timearray.length(); l++) {
					timestore = timearray.getJSONObject(l);
					start = timestore.getString("startTime");
					end = timestore.getString("endTime");
					response = ad.updateRequests((date+" "+start), (date+" "+end), id);
					if(response.equals("failure")) {
						reply.put("result", "failure");
						return reply;
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

}
