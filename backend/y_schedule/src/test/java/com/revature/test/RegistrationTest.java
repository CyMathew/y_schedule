package com.revature.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.mockito.Mockito;

import beans.UserBean;
import daos.RegistrationDao;
import services.RegistrationService;
import servlet.RegistrationServlet;

public class RegistrationTest {


	@Test
	public void RegistrationServiceTest() {
		RegistrationDao beantest = Mockito.mock(RegistrationDao.class);
		Mockito.when(beantest.createNewEmployee("blech", "blech", "blech", "blech", (Integer)1)).thenReturn(new UserBean(new Integer(0), "blech", "blech", "blech", "blech", new Integer(1), new Integer(2367)));
		
		RegistrationService testserv = new RegistrationService();
		testserv.setImpl(beantest);
		assertNotNull(testserv.getImpl());
		assertEquals("success", testserv.CreateNewEmployee("blech", "blech", "blech", "blech",(Integer)1).get("result"));
		
		
	}

}
