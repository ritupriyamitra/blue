package com.lazybuds.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	private User user;

	@Before
	public void objectCreation() {
		user = new User();

	}

	@Test
	public void toStrintTest() {

		user.setUserId(1);
		user.setFirstName("Ritu");
		user.setLastName("Mitra");

		String expectedUser = "User [userId=" + 1 + ", firstName=" + "Ritu" + ", lastName=" + "Mitra" + "]";
		assertEquals(expectedUser, user.toString());

	}

	@Test
	public void toStrintTestNullCheck() {

		String expectedUser = "User [userId=" + 0 + ", firstName=" + null + ", lastName=" + null + "]";
		assertEquals(expectedUser, user.toString());

	}

}
