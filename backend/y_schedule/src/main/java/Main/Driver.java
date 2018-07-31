package Main;

import java.sql.Timestamp;

import daos.ManagerDao;
import daos.UserDao;

public class Driver {
	public static void main(String[] args) {
		ManagerDao md = new ManagerDao();
		UserDao ud = new UserDao();
		Timestamp s = new Timestamp(0);
		md.createScheduleTimeBean(5, s, s, ud.getUserById(4));
	}
}
