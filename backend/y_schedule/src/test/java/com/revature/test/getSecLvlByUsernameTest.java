package com.revature.test;

import static org.junit.Assert.*;

import daos.UserDao;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class getSecLvlByUsernameTest {
	final static Logger logger = Logger.getLogger(getSecLvlByUsernameTest.class);
	UserDao utbi = new UserDao();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getSecLvlTrue() {
		assertEquals(Integer.valueOf(1), utbi.getSecLvlByUsername("Milisdowered"));
		assertEquals(Integer.valueOf(3), utbi.getSecLvlByUsername("Swuzzy"));
		assertEquals(Integer.valueOf(2), utbi.getSecLvlByUsername("Posuraid68"));
	}
	
	@Test
	public void getSecLvlFalse() {
		assertNotEquals(Integer.valueOf(4), utbi.getSecLvlByUsername("Posuraid68"));
		assertNotEquals(Integer.valueOf(3), utbi.getSecLvlByUsername("Oferds"));
		assertNotEquals(Integer.valueOf(4), utbi.getSecLvlByUsername(null));
	}

	

}
