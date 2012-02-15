package com.friendster.api.client;

import static org.junit.Assert.assertNotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import com.friendster.api.client.throwable.FriendsterAPIError;

public class RequestTest {

	private static Request baseRequest;
	private static final String baseURLEndpoint = "http://www.friendster.com/api";

	@BeforeClass
	public static void initialize() throws MalformedURLException, FriendsterAPIError {
		baseRequest = new Request(new URL(RequestTest.baseURLEndpoint), "apiKey", "secretKey", "sessionKey");
	}

	/*
	 * Checks if request parameters passed are null
	 */
	@Test(expected = FriendsterAPIError.class)
	public void testNullRequest() throws MalformedURLException {
		new Request(new URL(RequestTest.baseURLEndpoint), null,  null, null);
	}

	/*
	 * Checks if signature generated is invalid
	 */
	@Test
	public void testInvalidGenerateSignature() throws MalformedURLException {
		new Request(new URL(RequestTest.baseURLEndpoint), "test", "", "test").generateSignature();
	}

	/*
	 * Checks if Nonce generated is valid
	 */
	@Test
	public void testValidGenerateNOnce() {
		assertNotNull(RequestTest.baseRequest.generateNOnce());
	}

	/*
	 * Checks if Nonce generated is invalid
	 */
	@Test(expected = NullPointerException.class)
	public void testInvalidNOnce() {
		class Request {
			protected UUID generateNOnce() {
				return null;
			}
		};

		new Request().generateNOnce().toString();
	}
}