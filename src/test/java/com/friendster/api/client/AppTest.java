package com.friendster.api.client;

import static org.junit.Assert.*;

import org.junit.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
	private App appInstance;

	@Before
	public void setUp() {
		appInstance = new App();
	}

	@Test
	public void testHello() {
		assertEquals("Hello", appInstance.hello());
	}
}