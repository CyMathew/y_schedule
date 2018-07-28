package com.revature.test;

import static org.junit.Assert.assertEquals;


import org.junit.Test;
import org.mockito.Mockito;

import daos.RegistrationDao;
import services.RegistrationService;
import servlet.RegistrationServlet;

public class RegistrationTest {


	@Test
	public void RegistrationServiceTest() {
		RegistrationDao beantest = Mockito.mock(RegistrationDao.class);
		Mockito.when(beantest.createNewEmployee("blech", "blech", "blech", "blech", (Integer)1)).thenReturn("Success");
		
		RegistrationServlet servlet = new RegistrationServlet();
		RegistrationService testserv = new RegistrationService();
		testserv.setImpl(beantest);
		assertEquals("success", testserv.CreateNewEmployee("blech", "blech", "blech", "blech",(Integer)1).get("result"));
		
		
	}

}
