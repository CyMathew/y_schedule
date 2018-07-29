package main;

import beans.EmployeeAvailabilityBean;
import beans.UserBean;
import daos.EmployeeDao;

public class Driver {

	public static void main(String[] args) {
		EmployeeDao empdao = new EmployeeDao();
		UserBean UD = new UserBean("tik", "tak", "blik", "username");
		UD.setSec_lvl(1);
		UD.setStoreId(2367);
		UD.setUser_id(31);
		System.out.println(empdao.removeAllReguests(31));
		System.exit(0);
	}

}
