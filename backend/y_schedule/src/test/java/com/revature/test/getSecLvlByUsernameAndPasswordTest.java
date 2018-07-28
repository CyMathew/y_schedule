package com.revature.test;

import static org.junit.Assert.assertEquals;

import beans.UserBean;
import daos.UserDao;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class getSecLvlByUsernameAndPasswordTest {
	final static Logger logger = Logger.getLogger(getSecLvlByUsernameAndPasswordTest.class);
	UserDao utbi = new UserDao();
	UserBean bean = null;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getSecLvlByUserPassTrueEmp() {
		assertEquals(bean, utbi.getSecLvlByUsernameAndPassword("Pinkston", "PASSWORD"));
	}
	
	@Test
	public void getSecLvlByUserPassFalseEmp() {
		assertEquals(null, utbi.getSecLvlByUsernameAndPassword("TEST", "TEST"));
	}
	
	@Test
	public void getSecLvlByUserPassFalseEmpUuser() {
		assertEquals(null, utbi.getSecLvlByUsernameAndPassword("Pinkston", "TEST"));
	}
	
	@Test
	public void getSecLvlByUserPassFalseEmpPass() {
		assertEquals(null, utbi.getSecLvlByUsernameAndPassword("ETET", "PASSWORD"));
	}
	
}
