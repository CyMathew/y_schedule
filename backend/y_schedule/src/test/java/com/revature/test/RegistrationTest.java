package com.revature.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.mockito.Mockito;

import beans.UserBean;
import daos.RegistrationDao;
import services.EmployeeService;
import services.RegistrationService;
import servlet.RegistrationServlet;

public class RegistrationTest {


	@Test
	public void RegistrationServiceTest() {
		RegistrationDao beantest = Mockito.mock(RegistrationDao.class);
		EmployeeService es = Mockito.mock(EmployeeService.class);
		
		UserBean b = new UserBean(new Integer(0), "blech", "blech", "blech", "blech", new Integer(1), new Integer(2367));
		
		Mockito.when(beantest.createNewEmployee("blech", "blech", "blech", "blech", (Integer)1)).thenReturn(b);
		Mockito.doNothing().when(es).setDefaultAvailability(b);
		
		RegistrationService testserv = new RegistrationService();
		testserv.setImpl(beantest);
		testserv.setEs(es);
		assertNotNull(testserv.getImpl());
		assertEquals("success", testserv.CreateNewEmployee("blech", "blech", "blech", "blech",(Integer)1).get("result"));
		
		
	}

}
