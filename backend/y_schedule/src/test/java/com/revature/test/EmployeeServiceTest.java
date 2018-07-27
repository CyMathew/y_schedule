package com.revature.test;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import beans.User_TableBeans;
import service.EmployeeService;

public class EmployeeServiceTest {
	
	final static Logger logger = Logger.getLogger(getSecLvlByUsernameAndPasswordTest.class);
	static User_TableBeans  bean,bean2,bean3 = new  User_TableBeans();
	daos.User_TableBeansImpl utbi = new daos.User_TableBeansImpl();
	
	@Before
	public void setUp() throws Exception {
		bean = utbi.getUserById(1);
		bean2 = utbi.getUserById(2);
		bean3 = utbi.getUserById(3);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getUserBySecLevTrue() {
		assertEquals("coordinator", EmployeeService.getUserBySecLev(bean));
		assertEquals("manager", EmployeeService.getUserBySecLev(bean2));
		assertEquals("employee", EmployeeService.getUserBySecLev(bean3));
	}
	@Test
	public void getUserBySecLevFalse() {
		assertNotEquals("coordinator", EmployeeService.getUserBySecLev(bean3));
		assertNotEquals("manager", EmployeeService.getUserBySecLev(bean));
		assertNotEquals("employee", EmployeeService.getUserBySecLev(bean2));
	}

}
