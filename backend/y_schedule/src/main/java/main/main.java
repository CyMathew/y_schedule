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
		service.approveTimes(1);

		System.exit(0);
	}

}
