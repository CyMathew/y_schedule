package com.revature.test;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class getSecLvlByUsernameAndPasswordTest {
	final static Logger logger = Logger.getLogger(getSecLvlByUsernameAndPasswordTest.class);
	daos.User_TableBeansImpl utbi = new daos.User_TableBeansImpl();
//come back to latter
//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}
//
//	@Test
//	public void getSecLvlByUserPassTrueEmp() {
//		assertEquals(true, utbi.checkPassword("role", "employee"));
//	}
//	@Test
//	public void getSecLvlByUserPassMan() {
//		assertEquals(true, utbi.checkPassword("role", "manager"));
//	}
//	@Test
//	public void getSecLvlByUserPassTrueCo() {
//		assertEquals(true, utbi.checkPassword("role", "coordinator"));
//	}
}
