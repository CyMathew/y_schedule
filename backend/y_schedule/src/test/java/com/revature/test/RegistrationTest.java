package com.revature.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
		
		RegistrationService testserv = new RegistrationService();
		testserv.setImpl(beantest);
		assertNotNull(testserv.getImpl());
		assertEquals("Success", testserv.CreateNewEmployee("blech", "blech", "blech", "blech",(Integer)1).get("result"));
		
		
	}

}
