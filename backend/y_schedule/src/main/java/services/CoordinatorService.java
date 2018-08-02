package services;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import beans.EmployeeAvailabilityBean;
import beans.UserBean;
import daos.CoodinatorDao;
import daos.EmployeeDao;
import daos.UserDao;

public class CoordinatorService {

	private CoodinatorDao theDao  = new CoodinatorDao();
	private EmployeeDao empDao    = new EmployeeDao();
	private UserDao useDao        = new UserDao();
	private UserBean useBean      = new UserBean();
	
	public JSONObject gateKeeper(JSONObject requestJSON) {
		switch ((String)requestJSON.get("action")) {
			case "getAllRequests": return idsOfPeopleRequesting();
			
			case "getEmpAvail": return timesPendingForApprovalById(requestJSON.getInt("empID")).put("empName", requestJSON.get("empName"));
			
			case "requestAction": if(requestJSON.get("requestStatus").equals("Approved")) 
									{return approveTimes(requestJSON.getInt("empID"));}
								  else 
								  	{return denyTimes(requestJSON.getInt("empID"));}
			
			default: return new JSONObject().put("result", "Unknown Command Error");
		}
	}
	
	/**
	 * This will return a unique set of ids of employees requesting a change in their avalibility
	 **/
	public JSONObject idsOfPeopleRequesting() {
		JSONObject json = new JSONObject();
		JSONArray jarray = new JSONArray();
		List<EmployeeAvailabilityBean>list = theDao.getPendingRequests();		
		Set<Integer> set = new HashSet<>();
		for(int i = 0; i < list.size(); i++) {
			set.add((list.get(i).getUser()).getUser_id());
		}
		Iterator iter = set.iterator();
		while(iter.hasNext()) {

			useBean = useDao.getUserById((Integer) iter.next());

			jarray.put(new JSONObject().put("empID", useBean.getUser_id()).put("empName", useBean.getUser_fname()+" "+useBean.getUser_lname()));
		}
		
		json.put("requestList", jarray);
		return json;
	}
	
	/**
	 *This approves the changes to the times that an employee has.  
	 **/
	public JSONObject approveTimes(Integer id) {
		if( empDao.approveNewAvail(id)) {
			return new JSONObject().put("result", "success");
		}
		return new JSONObject().put("result", "failure");
	}
	
	/**
	 * This denies the new changes the employee has made to their availiblity time. 
	 **/
	public JSONObject denyTimes(Integer id) {
		if( empDao.denyRequests(id)) {
			return new JSONObject().put("result", "success");
		}
		return new JSONObject().put("result", "failure");
	}
	
	/**
	 * This receives the pending changes for an employee identified by id 
	 **/
	public JSONObject timesPendingForApprovalById(Integer id) {
		JSONObject weekdetails = new JSONObject();
		JSONArray weekarray = new JSONArray();
		List<EmployeeAvailabilityBean> list = empDao.getPendingTimesCoor(id);
		for (int i = 0; i < list.size(); i++) {
			weekarray.put(new JSONObject().put("day", list.get(i).getDay()).put("startTime", list.get(i).getStart())
					.put("endTime", list.get(i).getEnd()));
		}
		weekdetails.put("weekDetails", weekarray);
		return weekdetails;
		
	}

	public CoodinatorDao getTheDao() {
		return theDao;
	}

	public void setTheDao(CoodinatorDao theDao) {
		this.theDao = theDao;
	}

	public EmployeeDao getEmpDao() {
		return empDao;
	}

	public void setEmpDao(EmployeeDao empDao) {
		this.empDao = empDao;
	}

	public UserDao getUseDao() {
		return useDao;
	}

	public void setUseDao(UserDao useDao) {
		this.useDao = useDao;
	}

	public UserBean getUseBean() {
		return useBean;
	}

	public void setUseBean(UserBean useBean) {
		this.useBean = useBean;
	}
	
}



 /*
  * n size 
  * */