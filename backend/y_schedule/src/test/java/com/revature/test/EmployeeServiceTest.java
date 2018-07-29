package com.revature.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import beans.EmployeeAvailabilityBean;
import beans.UserBean;
import daos.EmployeeDao;

public class EmployeeServiceTest {

	@Test
	public void test() {
		UserBean b = new UserBean("Dad", "Master", "d", "dadma");
		b.setUser_id(43);
		EmployeeAvailabilityBean e1 = new EmployeeAvailabilityBean("9:00", "12:00", b, "monday");
		EmployeeDao empdao = Mockito.mock(EmployeeDao.class);
		
		assertEquals(true, empdao.removeAllReguests(43));
	}

}
