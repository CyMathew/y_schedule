package com.revature.test;

import static org.junit.Assert.*;

import beans.UserBean;
import daos.UserDao;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import services.UserService;

public class UserServiceTest {
	
	final static Logger logger = Logger.getLogger(getSecLvlByUsernameAndPasswordTest.class);
	static UserBean bean,bean2,bean3 = new UserBean();
	UserDao utbi = new UserDao();
	
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
		assertEquals("coordinator", UserService.getRoleName(bean));
		assertEquals("manager", UserService.getRoleName(bean2));
		assertEquals("employee", UserService.getRoleName(bean3));
	}
	@Test
	public void getUserBySecLevFalse() {
		assertNotEquals("coordinator", UserService.getRoleName(bean3));
		assertNotEquals("manager", UserService.getRoleName(bean));
		assertNotEquals("employee", UserService.getRoleName(bean2));
	}

}
