package com.friendster.api.client.parser;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.sax.SAXSource;

import org.apache.http.HttpEntity;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.friendster.api.client.builders.AvatarScoreBuilder;
import com.friendster.api.client.enums.RequestTypesEnum;
import com.friendster.api.client.parser.xml.NamespaceFilter;
import com.friendster.api.client.special.AvatarScoreResponse;
import com.friendster.api.client.throwable.FriendsterAPIException;
import com.friendster.api.v1.GameScoreResponse;
import com.friendster.api.v1.ShoutoutResponse;
import com.friendster.api.v1.UserResponse;
import com.friendster.api.v1.app.ApplicationFriendsResponse;
import com.friendster.api.v1.friends.FriendsResponse;
import com.friendster.api.v1.message.MessageResponse;
import com.friendster.api.v1.notification.NotificationsResponse;

@SuppressWarnings("restriction")
public class FriendsterAPIXMLResponseParser implements
		FriendsterAPIResponseParserInterface {
	public Object parseResponse(RequestTypesEnum requestType,
			HttpEntity httpInput) {
		Object tempObject = null;

		try {
			JAXBContext jc = this.getJAXBContext(requestType);
			Unmarshaller u = jc.createUnmarshaller();
			XMLReader reader = XMLReaderFactory.createXMLReader();
			NamespaceFilter inFilter = this.getNamespaceFilter(requestType);
			inFilter.setParent(reader);
			InputSource is = new InputSource(httpInput.getContent());
			SAXSource source = new SAXSource(inFilter, is);
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
		case TOP_SCORES:
			return (AvatarScoreResponse) AvatarScoreBuilder.buildAvatarScore((GameScoreResponse) tempObject);
		case SCORE:
			return (com.friendster.api.v1.score.GameScoreResponse) tempObject;
		case MESSAGE:
			return (MessageResponse) tempObject;
		case MESSAGE_P:
			return (MessageResponse) tempObject;
		case MESSAGES:
			return (com.friendster.api.v1.messages_get.MessageResponse) tempObject;
		case NOTIFICATION_P:
			return (NotificationsResponse) tempObject;
		case APP_FRIENDS:
			return (ApplicationFriendsResponse) tempObject;
		default:
			throw new FriendsterAPIException();
		}
	}

	private JAXBContext getJAXBContext(RequestTypesEnum requestType)
			throws JAXBException {
		switch (requestType) {
		case APP_FRIENDS:
			return JAXBContext.newInstance("com.friendster.api.v1.app");
		case FRIENDS:
			return JAXBContext.newInstance("com.friendster.api.v1.friends");
		case SCORE:
			return JAXBContext.newInstance("com.friendster.api.v1.score");
		case MESSAGE:
		case MESSAGE_P:
			return JAXBContext.newInstance("com.friendster.api.v1.message");
		case MESSAGES:
			return JAXBContext.newInstance("com.friendster.api.v1.messages_get");
		case USER:
		case SHOUTOUT_P:
		case TOP_SCORES:
		case SHOUTOUT:
			return JAXBContext.newInstance("com.friendster.api.v1");
		case NOTIFICATION_P:
			return JAXBContext.newInstance("com.friendster.api.v1.notification");
		default:
			throw new FriendsterAPIException();
		}
	}
	
	private NamespaceFilter getNamespaceFilter(RequestTypesEnum requestType) {
		switch (requestType) {
		case APP_FRIENDS:
			return new NamespaceFilter(
					"http://api.friendster.com/v1/app", true);
		case FRIENDS:
			return new NamespaceFilter(
					"http://api.friendster.com/v1/friends", true);
		case SCORE:
			return new NamespaceFilter("http://api.friendster.com/v1/score", true);
		case MESSAGE:
		case MESSAGE_P:
			return new NamespaceFilter(
					"http://api.friendster.com/v1/message", true);
		case MESSAGES:
			return new NamespaceFilter("http://api.friendster.com/v1/messages_get", true);
		case USER:
		case SHOUTOUT_P:
		case TOP_SCORES:
		case SHOUTOUT:
			return new NamespaceFilter("http://api.friendster.com/v1/", true);
		case NOTIFICATION_P:
			return new NamespaceFilter("http://api.friendster.com/v1/notification", true);
		default:
			throw new FriendsterAPIException();
		}
	}
}