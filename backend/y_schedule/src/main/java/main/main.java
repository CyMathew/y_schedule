package main;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.json.JSONObject;

import daos.CoodinatorDao;
import daos.EmployeeDao;
import daos.ManagerDao;
import services.CoordinatorService;
import services.EmployeeService;

public class main {

	public static void main(String[] args) {
		/*CoordinatorService service = new CoordinatorService();
		JSONObject test = null;
		test = service.idsOfPeopleRequesting();
		System.out.println(test.getJSONArray("ids").get(0));
		System.out.println(test.getJSONArray("ids").get(1));*/
		
		CoordinatorService service = new CoordinatorService();
//		System.out.println(service.idsOfPeopleRequesting());
//		System.out.println(service.timesPendingForApprovalById(61));
		JSONObject command = new JSONObject();
		command.put("action", "getAllRequests");
		System.out.println(service.gateKeeper(command));
		System.exit(0);
	}

}
