package com.friendster.api.client.parser;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.http.HttpEntity;
import org.apache.log4j.Logger;

import com.friendster.api.client.enums.RequestTypesEnum;
import com.friendster.api.client.throwable.FriendsterAPIException;
import com.friendster.api.v1.UserResponseType;

@SuppressWarnings("restriction")
public class FriendsterAPIXMLResponseParser implements
		FriendsterAPIResponseParserInterface {
	private static Logger logger = Logger
			.getLogger(FriendsterAPIXMLResponseParser.class);

	@SuppressWarnings("rawtypes")
	public Object parseResponse(RequestTypesEnum requestType,
			HttpEntity httpInput) {
		JAXBElement element = null;
		try {
			InputStream is = httpInput.getContent();
			JAXBContext jc = JAXBContext.newInstance("com.friendster.api.v1");
			Unmarshaller u = jc.createUnmarshaller();
			logger.info("Completed Unmarshalling to a specified type.");
			element = (JAXBElement) u.unmarshal(is);
		} catch (JAXBException e) {
			throw new FriendsterAPIException(e);
		} catch (IllegalStateException e) {
			throw new FriendsterAPIException(e);
		} catch (IOException e) {
			throw new FriendsterAPIException(e);
		}

		switch (requestType) {
		case USER:
			return (UserResponseType) element.getValue();
		default:
			throw new FriendsterAPIException();
		}
	}
}