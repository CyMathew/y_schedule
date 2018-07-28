package com.revature.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;

import beans.UserBean;
import daos.UserDao;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class getUserByIdTest {
	final static Logger logger = Logger.getLogger(getSecLvlByUsernameAndPasswordTest.class);
	static UserBean bean,bean2,bean3,bean4 = new UserBean();
	UserDao utbi = new UserDao();
	
	
	@Before
	public void setUp() throws Exception {
		bean = utbi.getUserById(1);
		bean2 = utbi.getUserById(2);
		bean3 = utbi.getUserById(3);
		bean4 = utbi.getUserById(4);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getUserByIdTrue() {
		assertEquals((Integer)1, bean.getUser_id());
		assertEquals((Integer)2, bean2.getUser_id());
		assertEquals((Integer)3, bean3.getUser_id());
		assertEquals((Integer)4, bean4.getUser_id());
	}
	@Test
	public void getUserByIdFalsue() {
		assertNotEquals((Integer)2, bean.getUser_id());
		assertNotEquals((Integer)10, bean.getUser_id());
		assertNotEquals((Integer)50, bean.getUser_id());
		assertNotEquals((Integer)100, bean.getUser_id());
	}

}
