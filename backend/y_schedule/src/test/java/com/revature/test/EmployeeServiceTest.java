package com.revature.test;

import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import beans.EmployeeAvailabilityBean;
import beans.UserBean;
import daos.EmployeeDao;

public class EmployeeServiceTest {

	@Test
	public void test() {
		List <EmployeeAvailabilityBean>l = null; 
		UserBean b = new UserBean("Kevin", "Wang", "Psswrd", "wang3224");
		EmployeeAvailabilityBean e1 = new EmployeeAvailabilityBean("9:00", "12:00", b, "monday");
		l.add(e1);
		EmployeeDao empdao = Mockito.mock(EmployeeDao.class);
		Mockito.when(empdao.removeAllReguests(1)).thenReturn(true);
		Mockito.when(empdao.getStartTimesById(1)).thenReturn((List) e1);
	}

}
