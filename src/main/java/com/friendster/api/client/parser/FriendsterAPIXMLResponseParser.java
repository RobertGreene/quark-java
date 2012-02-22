package com.friendster.api.client.parser;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.sax.SAXSource;

import org.apache.http.HttpEntity;
import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.friendster.api.client.enums.RequestTypesEnum;
import com.friendster.api.client.parser.xml.NamespaceFilter;
import com.friendster.api.client.throwable.FriendsterAPIException;
import com.friendster.api.v1.ShoutoutResponse;
import com.friendster.api.v1.UserResponse;
import com.friendster.api.v1.friends.FriendsResponse;

@SuppressWarnings("restriction")
public class FriendsterAPIXMLResponseParser implements
		FriendsterAPIResponseParserInterface {
	private static Logger logger = Logger
			.getLogger(FriendsterAPIXMLResponseParser.class);

	public Object parseResponse(RequestTypesEnum requestType,
			HttpEntity httpInput) {
		Object tempObject = null;

		try {
			// Prepare JAXB objects
			JAXBContext jc = this.getJAXBContext(requestType);
			Unmarshaller u = jc.createUnmarshaller();

			// Create an XMLReader to use with our filter
			XMLReader reader = XMLReaderFactory.createXMLReader();

			// Create the filter (to add namespace) and set the xmlReader as its
			// parent.
			NamespaceFilter inFilter = this.getNamespaceFilter(requestType);
			inFilter.setParent(reader);

			// Prepare the input, in this case a java.io.File (output)
			InputSource is = new InputSource(httpInput.getContent());

			// Create a SAXSource specifying the filter
			SAXSource source = new SAXSource(inFilter, is);

			// Do unmarshalling
			tempObject = u.unmarshal(source);

		} catch (JAXBException e) {
			e.printStackTrace();
			throw new FriendsterAPIException(e);
		} catch (SAXException e) {
			throw new FriendsterAPIException(e);
		} catch (IllegalStateException e) {
			throw new FriendsterAPIException(e);
		} catch (IOException e) {
			throw new FriendsterAPIException(e);
		}

		switch (requestType) {
		case USER:
			return (UserResponse) tempObject;
		case SHOUTOUT_P:
			return (ShoutoutResponse) tempObject;
		case FRIENDS:
			return (FriendsResponse) tempObject;
		default:
			throw new FriendsterAPIException();
		}
	}

	private JAXBContext getJAXBContext(RequestTypesEnum requestType)
			throws JAXBException {
		switch (requestType) {
		case FRIENDS:
			return JAXBContext.newInstance("com.friendster.api.v1.friends");
		case USER:
		case SHOUTOUT_P:
			return JAXBContext.newInstance("com.friendster.api.v1");
		default:
			throw new FriendsterAPIException();
		}
	}
	
	private NamespaceFilter getNamespaceFilter(RequestTypesEnum requestType) {
		switch (requestType) {
		case FRIENDS:
			return new NamespaceFilter(
					"http://api.friendster.com/v1/friends", true);
		case USER:
		case SHOUTOUT_P:
			return new NamespaceFilter(
					"http://api.friendster.com/v1/", true);
		default:
			throw new FriendsterAPIException();
		}
	}
}