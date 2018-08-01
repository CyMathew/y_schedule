package services;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import beans.EmployeeAvailabilityBean;
import daos.CoodinatorDao;
import daos.EmployeeDao;

public class CoordinatorService {

	private CoodinatorDao theDao = new CoodinatorDao();
	private EmployeeDao empDao = new EmployeeDao();
	
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
			jarray.put(iter.next());
		}
		
		json.put("ids", jarray);
		return json;
	}
	
	public boolean approveTimes(Integer id) {
		return empDao.approveNewAvail(id);
	}
}



 /*
  * n size 
  * */